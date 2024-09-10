package Graduation.work.YongduriMarketServer.service;

import Graduation.work.YongduriMarketServer.domain.ChatRoom;
import Graduation.work.YongduriMarketServer.domain.Search;
import Graduation.work.YongduriMarketServer.domain.User;
import Graduation.work.YongduriMarketServer.dto.ChatRoomRequestDto;
import Graduation.work.YongduriMarketServer.dto.SearchRequestDto;
import Graduation.work.YongduriMarketServer.dto.SearchResponseDto;
import Graduation.work.YongduriMarketServer.exception.CustomException;
import Graduation.work.YongduriMarketServer.exception.ErrorCode;
import Graduation.work.YongduriMarketServer.repository.SearchRepository;
import Graduation.work.YongduriMarketServer.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SearchService {
    private final SearchRepository searchRepository;
    private final UserRepository userRepository;



    //전체조회
    public List <SearchResponseDto>getAllKeyword() throws Exception{
        List<Search> search = searchRepository.findByOrderByCreatedAtDesc();
        return search.stream()
                .map(SearchResponseDto::getSearchDto)
                .collect(Collectors.toList());
    }
    //관련 키워드 조회
    public List<SearchResponseDto> getKeyword(Long studentId, SearchRequestDto.CheckDto request) {
        User user = findByStudentId(studentId);
        //400 데이터 미입력
        if(request.getKeyword().isEmpty()){
            throw new CustomException(ErrorCode.INSUFFICIENT_DATA);
        }
        List<Search> searchKeyword = findByKeywordContaining(request.getKeyword());
        return searchKeyword.stream()
                .map(SearchResponseDto::getSearchDto)
                .collect(Collectors.toList());
    }


    //검색어 등록
    public Boolean registerKeyword(Long studentId, SearchRequestDto.RegisterDto request) throws Exception{
        User user = findByStudentId(studentId);

        //400 데이터 미입력
        if(request.getKeyword().isEmpty()){
            throw new CustomException(ErrorCode.INSUFFICIENT_DATA);
        }
        try {
            Search search = Search.builder()
                    .userId(user)
                    .keyword(request.getKeyword())
                    .build();
            searchRepository.save(search);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }




    //검색어 삭제
    public Boolean deleteKeyword(Long studentId, SearchRequestDto.DeleteDto request) throws Exception{

        User user = findByStudentId(studentId);
        Search search = findBySearchId(request.getSearchId());
        //400 데이터 미입력
        if(request.getSearchId() == null){
            throw new CustomException(ErrorCode.INSUFFICIENT_DATA);
        }
        try{
            searchRepository.deleteById(request.getSearchId());
            return true;
        }catch (Exception e){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    private User findByStudentId(Long studentId) {
        return userRepository.findByStudentId(studentId)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_EXIST_MEMBER));
    }
    public Search findBySearchId(Long searchId) {
        return searchRepository.findBySearchId(searchId)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_EXIST_ID));
    }

    public List<Search>  findByKeywordContaining(String keyword) {
        return searchRepository.findByKeywordContaining(keyword);
    }

}
