# fillRandomODArray

```plantuml
@startuml
:Начало fillRandomODArray;
:size = readInt(); <<procedure>> 
:array= new int[size]; <<task>> 
while (i=0; i < size?; i++) is (да)
  :array[i] = generateRandomValue(); <<procedure>> 
endwhile (нет)
:printODArray(); <<procedure>> 
:Конец;
@enduml
```

# fillUserODArray

```plantuml
@startuml
:Начало fillUserODArray;
:size = readInt(); <<procedure>> 
:array= new int[size]; <<task>> 
while (i=0; i < size?; i++) is (да)
  :array[i] = readInt(); <<procedure>> 
endwhile (нет)
:printODArray(); <<procedure>> 
:Конец;
@enduml
```