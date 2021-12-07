# EAT_Client
2021-2 Software Engineering Course

## Platform
- Android moblie


## Tool
- Android Studio Arctic Fox | 2020.3.1 Patch 2


## Dependencies
```
dependencies {
    implementation 'androidx.appcompat:appcompat:1.3.1'
    implementation 'com.google.android.material:material:1.4.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.1'
    implementation 'androidx.navigation:navigation-fragment:2.3.5'
    implementation 'androidx.navigation:navigation-ui:2.3.5'
    testImplementation 'junit:junit:4.+'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
    implementation 'com.squareup.retrofit:retrofit:1.9.0'
    implementation 'com.squareup.okhttp:okhttp:2.0.0'
    implementation group: 'com.squareup.retrofit2', name: 'converter-gson', version: '2.8.1'
    implementation group: 'com.google.code.gson', name: 'gson', version: '2.8.6'
    implementation 'com.squareup.okhttp3:okhttp-urlconnection:4.9.0'
}
```


## Features
Client는 Server와 통신하여 데이터를 주고받고 UI만 구성됩니다.
- Client에서 구현된 기능
 1. 회원가입
 2. 로그인
 3. 선호도(입맛) 조사
 4. 프로필
 5. 방 생성
 6. 방 접속
 7. 자동매칭 (추천매칭)

- Client에서 미구현된 기능
 1. Setup Appointment (채팅, 약속 확정 등 방접속 이후 작업들)
 2. 피드백 (상대방 및 자동매칭에 대한 피드백)
 3. 식당 추천 (사용자에게 맞는 식당 리스트를 추천)


## Description
1. 회원가입
    - 새로운 사용자는 주소, 이메일, 닉네임, 학번, ID, PW, 성별을 입력하여 가입할 수 있습니다.
2. 로그인
    - 기존 사용자는 ID, PW로 로그인할 수 있습니다.
3. 선호도(입맛) 조사
    - 회원가입 시 조사합니다. 1순위부터 5순위까지 음식 카테고리를 고릅니다. 이 정보는 자동매칭의 정보로 사용됩니다.
4. 프로필
    - 가입한 정보와 선호도 조사를 기반으로 프로필 정보를 볼 수 있습니다.
5. 방 생성
    - 음식 카테고리와 약속 시간, 식당을 선택하여 방을 생성할 수 있습니다. 방을 생성한 사용자는 자동으로 만들어진 방에 접속됩니다.
6. 방 접속
    - 방 리스트에서 다른 사용자가 만든 방을 보고 원하는 방을 선택하여 접속합니다.
7. 자동매칭 (추천매칭)
    - 사용자에게 맞는 상대방이 만든 방을 최대 3개 추천하고, 사용자는 이 중에 원하는 방을 선택하여 접속합니다. 추천 알고리즘은 서버딴에서 계산합니다.
