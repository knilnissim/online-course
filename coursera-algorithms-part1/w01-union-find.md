# Union-Find
1주차 11/17 - 11/23

**Steps to developing a usable algo**
1. Model a problem
2. Find an algo
3. Measure algo performance(time, space)
4. Analysis an algo
5. Address problems

## Dynamic Connectivity
### 문제 모델링
N개의 Object 집합이 주어졌을 때
* Union 커맨드: 두 오브젝트를 연결
* Find(connected) 쿼리: 두 오브젝트 사이 연결된 경로가 있는가 질의

#### 가정과 명시
* 두 오브젝트 p와 q가 연결되었다고할 때 그 연결은 반사적, 대칭적, 전이적이다.
* 서로(상호) 연결된 오브젝트들의 최대의 집합을 연결된 구성요소라고 한다.

#### 문제 구상화
* Union 커맨드: 두 오브젝트의 연결된 구성요소들을 합집합 구성요소로 대체시키는 것
* Find 쿼리: 두 오브젝트가 같은 구성요소에 속해있는지 확인

### 알고리즘 찾기
#### 고려사항
* 효율적 자료구조 찾기
* N개의 오브젝트는 엄청나게 많을 수 있다.
* M번의 연산이 엄청나게 많을 수 있다.
* Union 커맨드와 Find 쿼리는 섞어쓸 수 있다.

#### 준비; 테스트 클라이언트 만들기
* 입력을 받고 알고리즘의 동작을 확인할 수 있는 출력을 하는 클라이언트를 만든다.
