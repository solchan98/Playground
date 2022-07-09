# [BATCH-1] 기본적인 작업과 스텝 생성 및 API요청으로 Batch 수행해보기

(간단 정리)

Spring Batch는 위 전략을 제공해준다.  
- **블록를 Chunk**라고 하고,
- 수행하는 **작업의 묶음을 Job**이라고 하며, 
- 각 **작업 단계를 Step**이라고 한다.

시나리오
>  현재 데이터베이스에는 10명의 User가 존재하고 10명의 이름과 나이를 5명씩 나누어 로그에 남기는 배치 작업을 수행한다.

## BatchConfig

- 수행할 Job과 Step을 Bean으로 등록해야하기 때문에, @Configuration을 통해 스프링에 설정파일로 등록한다.  
- 작업이 2개 이상인 경우, 등록되는 Bean의 이름을 지정하여 등록한다. (Step은 Job에서 구분하여 호출하기 때문에 따로 Bean이름을 지정하지 않아도 된다.  )  
- Step은 다음의 3단계로 수행된다.
  - Reader: DB에서 수행할 데이터를 모두 가져온다. (10개인 경우, 10개)  
  - Process: 수행할 로직을 수행한다.
  - Writer: 하나의 Block이 끝난 후, 실행되는 부분이다. (데이터가 총 10개, chunk가 5이면 block 하나는 총 5개의 데이터)
    
    
## Account, AccountRepository

가장 간단하게 id, 나이, 이름으로 구성된 도메인과 Repository이다.

## AccountController

배치 수행을 동적으로 동작시켜 테스트해보기 위한 수단으로 사용하였다.  
스프링에서는 실제로 API로 배치를 수행하는 방식은 권하지 않는다고 한다.

JobLauncher와 Bean으로 등록한 Job을 주입받아서 동적으로 실행시킨다.
JobParametersBuilder는 Job의 인스턴스 값을 매 번 새로 만들어 중복으로 인해 배치가 작동되지 않는 문제를 해결한다.

## Batch DataBase

지금은 정리하지 않았지만, Batch를 사용하면서 Batch Meta Table은 작업의 성공여부 및 상태를 알 수 있기 때문에 추후 정리가 필요하다.