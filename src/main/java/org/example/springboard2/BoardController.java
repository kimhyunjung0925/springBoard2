package org.example.springboard2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService service;

    @GetMapping("/list")
    public void list(Model model) {
        List<BoardEntity> list = service.selboardList();
        model.addAttribute("list",list);
        //model.addAttribute("list",service.selboardList());
    }

    @GetMapping("/detail")
    public void datail(Model model, BoardEntity entity){
        service.updBoardHitUp(entity);
        model.addAttribute("data",service.selBoard(entity));
    }

    @GetMapping("/write")
    public void write(){}

    @PostMapping("/write")
    public String writeProc(BoardEntity entity, RedirectAttributes reAttr) {
        System.out.println(entity);
        int result = service.insBoard(entity);
        if (result == 0 ){
            //RedirectAttributes redirect랑 관련있음
            //reAttr.addAttribute("msg","글 등록에 실패하였습니다."); addAtturibute는 쿼리스크리 생성
            reAttr.addFlashAttribute("msg","글 등록에 실패하였습니다.");//addFlashAttribute는 request에 담아서 보내준다
            reAttr.addFlashAttribute("data",entity);
            return "redirect:/board/write";
        }
        return "redirect:/board/list";
    }

    @GetMapping("/del")
    public String delProc(BoardEntity entity, RedirectAttributes reAttr){
        int result = service.delBoard(entity);
        if (result == 0 ){
            //redirect랑 관련있음
            reAttr.addFlashAttribute("iboard",entity.getIboard());
            reAttr.addFlashAttribute("msg","글 삭제에 실패하였습니다.");//addFlashAttribute는 request에 담아서 보내준다
            return "redirect:/board/detail";
        }
        return "redirect:/board/list";
    }

    @GetMapping("/mod")
    public void mod(Model model, BoardEntity entity){
        model.addAttribute("data",service.selBoard(entity));
    }

    @PostMapping("/mod")
    public String modProc(BoardEntity entity){
        int result = service.updBoard(entity);
        return "redirect:/board/detail?iboard=" + entity.getIboard();
    }
}
