# , => space
## 1. 빌드 스테이지 시작
FROM gradle:7.6.1-jdk17-alpine AS build

# 컨테이너 내부(alpine 리눅스 환경)의 /app 폴더로 설정
WORKDIR /app

# 현재 디렉토리 내의 모든 파일과 폴더를 컨테이너의 /app 으로 복사
COPY . .

# gradle을 사용하여 프로젝트를 빌드(daemon 프로세스 사용 안함 (필수 X))
RUN gradle clean build --no-daemon


## 2. 실행 스테이지 시작
# openjdk 17 버전의 이미지를 가져와 JVM 환경 구축
FROM openjdk:17-alpine

# 2-1. 빌드를 미리 수동으로 프로젝트에서 하고 이미지를 구축할 시
#COPY build/libs/*.jar app.jar

# 2-2. 빌드를 따로 수동으로 하지 않고 이미지를 구축할 시(docker build)
COPY --from=build /app/build/libs/*.jar ./

# *.jar 파일을 나열하고 grep을 사용해 'plain'이라는 단어가 포함되지 않은 줄을 선택해 app.jar로 변경
RUN mv $(ls *.jar | grep -v plain) app.jar

# app.jar를 리눅스 환경에서 실행(스프링 부트 서버 실행(port 7777))
ENTRYPOINT ["java", "-jar", "app.jar"]

# docker run -d -p 8055:7777 --name first caolian0/sw_boot_project      caolian0 => docker Id