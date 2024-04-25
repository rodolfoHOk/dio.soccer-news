# Soccer News

> App Nativo Sobre Futebol Feminino com Android Jetpack da DIO

## Tecnologias

- Kotlin
- Android
- JetPack
- Retrofit (Http requests)
- Room (SQLite database)
- Hilt (Dependency Injection)

### Bibliotecas adicionadas

- com.squareup.retrofit2:retrofit
- com.squareup.retrofit2:converter-gson
- com.squareup.picasso:picasso
- androidx.room:room-runtime
- androidx.room:room-ktx
- androidx.room:room-compiler
- libs.androidx.swiperefreshlayout
- com.google.dagger.hilt.android
- com.google.dagger:hilt-android
- com.google.dagger:hilt-android-compiler

## Melhorias ou mudanças em relação as lives

- Uso Gradle com Kotlin ao invés do Groove por recomendação do Android Studio
- Uso do Kotlin ao invés do Java
- Uso do View Binding no RecyclerView Adapter
- Adicionado uma camada de repository ao invés de chamar a api diretamente no viewmodel
- Uso de funções assíncronas e coroutines do Kotlin
- Uso do ViewModelProvider.Factory para instanciar o viewmodel com parâmetros
- Mudando o ícone do favoritar quando é favorito ao invés de mudar a cor
- Quando busca todas as notícias o repositório já retorna se uma notícia é ou não uma favorita
- Uso do Hilt para injeção de dependências

## Rodar

### Requisitos

- Android Studio
- Dispositivo Android Virtual

### Comandos

- Android Studio / Menu / Run / Run 'app'

## Links para referências

- https://github.com/nglauber/books_jetpack

#### Temp

- Stop: Parte 4 - Vídeo 05 - Tempo 00:00
