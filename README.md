# EAT_Client

## Brief Introduction
- Eating AppointmenT(EAT) 프로젝트는 밥친구를 찾아주는 서비스를 제공합니다.



## Platform
- Android moblie



## Tool
- Android Studio Arctic Fox | 2020.3.1 Patch 2



## Client Developer
- InYong Shin
- Gyumin Jung



## Dependencies
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
    implementation group: 'com.squareup.retrofit2', name: 'converter-gson', version: '2.8.1' // JSON을 직렬화
    implementation group: 'com.google.code.gson', name: 'gson', version: '2.8.6' // 직렬화된 JSON을 객체로 역직렬화
    implementation 'com.squareup.okhttp3:okhttp-urlconnection:4.9.0'
}
