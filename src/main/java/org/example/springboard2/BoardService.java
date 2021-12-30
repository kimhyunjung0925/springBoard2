package org.example.springboard2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardMapper mapper;

    public int insBoard(BoardEntity entity){
        try {
            return mapper.insBoard(entity);
        } catch (Exception e){
            e.printStackTrace();}
        return 0;
    }

    public List<BoardEntity> selboardList(){
        return mapper.selBoardList();
    }

    public BoardEntity selBoard(BoardEntity entity){
        return mapper.selBoard(entity);
    }

    //조회수 올리기
    public void updBoardHitUp(BoardEntity entity){
        entity.setHits(1);
        mapper.updBoard(entity);
    }

    public int updBoard(BoardEntity entity) {return mapper.updBoard(entity); }

    public int delBoard(BoardEntity entity) {
        try {
            return mapper.delBoard(entity);
        } catch (Exception e){
        e.printStackTrace();}
        return 0;
    }
}
