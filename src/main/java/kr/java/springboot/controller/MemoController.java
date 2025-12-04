package kr.java.springboot.controller;

import kr.java.springboot.entity.Memo;
import kr.java.springboot.props.AppProps;
import kr.java.springboot.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller // ComponentScan용 + WebMVC를 위한
@RequestMapping
@RequiredArgsConstructor
public class MemoController {
    private final MemoRepository memoRepository;
    // final이 포함된 생성자가 @RequiredArgsConstructor을 통해서 생성됨

//    private final String msg = "나는 최고의 스프링 개발자!!";
    private final AppProps appProps;
    // ↓ application.yaml에서 설정한 후, @Value로 사용
    @Value("${app.msg}")
    private String msg;
    @Value("${app.year}")
    private int year;
    @Value("${app.skill[0]}")
    private String skill;
//    private List<String> skill; <- 이걸 사용하고 싶으면 따로 @ConfigurationProperties 해줘야해서 따른 파일로 관리

    @GetMapping
    public String index(Model model) {
//        model.addAttribute("msg", msg);
//        model.addAttribute("msg", msg + " " + year + " " + skill); <- @Value 사용시
        model.addAttribute("msg", appProps.getMsg() + " " + appProps.getYear() + " " + appProps.getSkill()); // <- @ConfigurationProperties 사용시, List으로 모든 값 가져올 수 있음!(AppProps + PropsConfig)
        model.addAttribute("memos", memoRepository.findAll());
        // Entity -> @Data -> @ToString으로 인해서 -> jsp에서 바로 <%= memos =%> 하면 됨.
        return "index";
    }

    @PostMapping
    public String form(@RequestParam String content) {
        Memo memo = new Memo();
        memo.setContent(content);
        memoRepository.save(memo);
        return "redirect:/";
    }
}
