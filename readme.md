# ๐ท๏ธ์คํ๋ง๋ถํธ - ํ์ผ์๋ก๋, ์์๋ก๊ทธ์ธ, ํด์ํ๊ทธ

### ๐ป ํ๋ก์ ํธ ์ธํ
![img.png](img.png)
<br>
![img_4.png](img_4.png)

### ๐ป ํ์ต๋ด์ฉ
* GenFile ํ๋๋ก ๋ชจ๋  ํ์ผ์ ๋ณด๋ฅผ ๋ค๋ฃฌ๋ค.
![img_1.png](img_1.png)

<br>

* ํ์ผ์ ๋ณด๋ด๋ ๋ฐฉ๋ฒ
    - ํ๋ฒ์ ๋ค ๋ณด๋ด๋ ๋ฐฉ๋ฒ
    - 2๋ฒ์ ๋๋  ๋ณด๋ด๋ ๋ฐฉ๋ฒ:  1) ํ์ผ๋ค๋ถํฐ ๋ชจ๋ ์๋ก๋  2) ์๋ต(๋ฒํธ, ๋ฒํธ) ,  3) ํผ์ ์ง์ด๋ฃ์ 4) ํผ ๋ฐ์ก(ํ์ผ๋ค์ ๋นผ๊ณ  ์๋ก๋)
       
<br>

* ํ์ผ์๋ก๋๋ method="POST" enctype="multipart/form-data"๊ฐ ํ์์ด๋ค. (GET X)

<br>

* ์ผ๋ฐ ์น๋ธ๋ผ์ฐ์ 
  - GET
  - POST

<br>

* POSTMAN ์ผ์ข์ ๋ธ๋ผ์ฐ์  (์น๊ฐ๋ฐ ํ  ๋ ๋ง์ด ์ฌ์ฉ๋๋ ํธ๋ฆฌํ ํด)
  - GET
  - POST
  - PATCH

<br>

![img_2.png](img_2.png)

<br>

![img_3.png](img_3.png)


* memberContext๋ฅผ ์ธ์์ ์ ์ฅํ๋ ์ด์ ๋ db์ฟผ๋ฆฌ ๋ง์ด ์๋ ๋ผ๊ฐ๊ฒ ํ๊ธฐ ์ํด์์ด๋ค.

<br>

* ์๋ ค์ผ ํ๋ ์ ๋ณด๊ฐ 2๊ฐ ์ด์์ด๋ฉด โก๏ธRsData๋ฅผ ์ฌ์ฉํ๋ ์ผ์ด์ค. (ex. ์ฑ๊ณต, ์คํจ, ๋ฉ์ธ์ง)

<br>

* ๊ฐ๋ฐ, ํ์คํธ, ์ด์ํ๊ฒฝ์ ๋ง๊ฒ ์ค์ ํ์ผ ๊ตฌ์กฐํ
  - ๊ฐ๋ฐํ๊ฒฝ
    - application.yml
    - application-base-addi.yml
    - application-dev.yml
  - ํ์คํธํ๊ฒฝ
    - application.yml
    - application-base-addi.yml
    - application.test.yml
  - ์ด์ํ๊ฒฝ
    - application.yml
    - application-base-addi.yml
    - application-prod.yml

### <์ค๋ฅํด๊ฒฐ>

#### 1. ์นด์นด์ค๋ก๊ทธ์ธ์ ์ํ ์์กด์ฑ ์ถ๊ฐ, ์ค์ ์ ์ค๋ฅ
![img_5.png](img_5.png)

```
Caused by: org.springframework.beans.factory.BeanCreationException: 
Error creating bean with name 'filterChain' defined in class path resource 
[com/ll/exam/app10/app/security/SecurityConfig.class]: Bean instantiation via factory method failed; 
nested exception is org.springframework.beans.BeanInstantiationException: 
Failed to instantiate [org.springframework.security.web.SecurityFilterChain]: 
Factory method 'filterChain' threw exception; nested exception is org.springframework.beans.factory.BeanCreationException: 
Error creating bean with name 'clientRegistrationRepository' defined in class path resource 
[org/springframework/boot/autoconfigure/security/oauth2/client/servlet/OAuth2ClientRegistrationRepositoryConfiguration.class]: 
Bean instantiation via factory method failed; nested exception is org.springframework.beans.BeanInstantiationException: 
Failed to instantiate [org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository]: 
Factory method 'clientRegistrationRepository' threw exception; nested exception is java.lang.IllegalStateException: 
Provider ID must be specified for client registration 'kakao'
```

<br>
โ ์ค๋ฅํด๊ฒฐ => application.yml์ clientId์ ์๋ฌด๊ฑฐ๋ ์. ==> ์ดํ ์ง์ฐ๊ณ  ๋ค์ ์คํํ๋๋ ๋ค์ ๋จ..?

<br>
<br>

#### 2. error: attempt to recreate a file for type

โ ์ค๋ฅํด๊ฒฐ

- build ํด๋๋ฅผ ์ญ์  ํ ๋ค์ ์คํํด๋ณธ๋ค.

![img_6.png](img_6.png)

- Reload All from Disk๋ฅผ ์คํํ๋ค.
![img_7.png](img_7.png)

- Repair IDE๋ฅผ ์คํํ๋ค.
![img_8.png](img_8.png)

- Invalidate Caches๋ฅผ ์คํํ๋ค.
![img_9.png](img_9.png)
![img_10.png](img_10.png)

- ๊ทธ๋๋ ์๋๋ฉด ๋ค์ ํด๋ก ๋ฐ์์ ์งํํ๋ค.

<br>
<br>

#### 3. ํน์  ํฌํธ๋ฅผ ์ ์ ํ๊ณ  ์๋ ํ๋ก์ธ์ค๊ฐ ์์ด ์ค๋ฅ ๋ฐ์
โ ์ค๋ฅํด๊ฒฐ => ํน์  ํฌํธ๋ฅผ ์ ์ ํ๊ณ  ์๋ ํ๋ก์ธ์ค ๋๋ ์๋์ฐ cmd ๋ช๋ น์ด

<br>

```
netstat -a -o | findstr 8010

8010 ํฌํธ๋ฅผ ์ฌ์ฉํ๋ ๋คํธ์ํฌ ์์๋ค ํ์

ํด๋น ํ๋ก์ธ์ค ๋ฒํธ ํ์ธ

taskkill /f /pid {ํ๋ก์ธ์ค๋ฒํธ}

EX : taskkill /f /pid 27208```

