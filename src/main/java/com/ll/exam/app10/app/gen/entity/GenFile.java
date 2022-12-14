package com.ll.exam.app10.app.gen.entity;

import com.ll.exam.app10.app.base.AppConfig;
import com.ll.exam.app10.app.base.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
// @AllArgsConstructor는 해당 객체 내에 있는 모든 변수들을 인수로 받는 생성자를 만들어내는 어노테이션이다
@AllArgsConstructor
@NoArgsConstructor
// @SuperBuilder어노테이션으로 상속되는 클래스의 Builder를 생성할 수 있다.
@SuperBuilder
// @ToString 어노테이션을 활용하면 클래스의 변수들을 기반으로 ToString 메소드를 자동으로 완성시켜 준다.
// 상위 클래스에 대해도 toString을 적용시키고자 한다면 상위 클래스에 @ToString(callSuper = true) 를 적용시키면 된다.
@ToString(callSuper = true)
public class GenFile extends BaseEntity {
    private String relTypeCode;
    private long relId;
    private String typeCode;
    private String type2Code;
    private String fileExtTypeCode;
    private String fileExtType2Code;
    private int fileSize;
    private int fileNo;
    private String fileExt;
    private String fileDir;
    private String originFileName;

    public String getFileName() {
        return getId() + "." + getFileExt();
    }

    public String getUrl() {
        return "/gen/" + getFileDir() + "/" + getFileName();
    }

    public String getDownloadUrl() {
        return "/download/gen/" + getId();
    }

    public void merge(GenFile other) {
        relTypeCode = other.getRelTypeCode(); // 어떤 게시물인지 알려줌 (ex. article 관련 글)
        relId = other.getRelId(); // 게시물 몇번인지
        typeCode = other.getTypeCode(); // ex. common
        type2Code = other.getType2Code(); // ex. inBody
        fileExtTypeCode = other.getFileExtTypeCode();
        fileExtType2Code = other.getFileExtType2Code();
        fileSize = other.getFileSize();
        fileNo = other.getFileNo(); // 본문이미지 number
        fileExt = other.getFileExt();
        fileDir = other.getFileDir();
        originFileName = other.getOriginFileName();
    }

    public String getFilePath() {
        return AppConfig.GET_FILE_DIR_PATH + "/" + getFileDir() + "/" + getFileName();
    }
}
