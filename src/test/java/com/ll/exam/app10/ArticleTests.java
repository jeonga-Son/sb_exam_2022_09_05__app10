package com.ll.exam.app10;

import com.ll.exam.app10.app.article.entity.Article;
import com.ll.exam.app10.app.article.service.ArticleService;
import com.ll.exam.app10.app.hashTag.entity.HashTag;
import com.ll.exam.app10.app.hashTag.service.HashTagService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class ArticleTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private HashTagService hashTagService;

    @Test
    @DisplayName("1번 게시물에는 해시태그가 2개 존재한다.")
    void t2() {
        Article article = articleService.getArticleById(1L);
        List<HashTag> hashTags = hashTagService.getHashTags(article);

        assertThat(hashTags.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("1번 게시물의 해시태그를 수정하면, 기존 해시태그 중 몇개는 지워질 수 있다.")
    @Rollback(false)
    void t3() {
        String keywordContentsStr = "#자바 #개발";
        Article article = articleService.getArticleById(1L);
        hashTagService.applyHashTags(article, keywordContentsStr);
    }
}