# Soccer News

> App Nativo Sobre Futebol Feminino com Android Jetpack da DIO

## Tecnologias

### Sem JetPack Compose (Branch without_compose)

- Kotlin
- Android
- JetPack
- Retrofit (Http requests)
- Room (SQLite database)
- Hilt (Dependency Injection)

### Com JetPack Compose (Branch main)

- Todas as tecnologias anteriores
- JetPack Compose

## Bibliotecas adicionadas

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

### Adicionais com JetPack Compose

- androidx.compose:compose-bom
- androidx.compose.material3:material3
- androidx.compose.ui:ui-tooling-preview
- androidx.compose.ui:ui-tooling (debug)
- androidx.compose.ui:ui-test-manifest (debug)
- androidx.compose:compose-bom (android test)
- androidx.compose.ui:ui-test-junit4 (android test)
- androidx.compose.runtime:runtime-livedata
- androidx.compose.material:material
- androidx.lifecycle:lifecycle-viewmodel-compose
- androidx.navigation:navigation-compose
- androidx.hilt:hilt-navigation-compose
- com.squareup.picasso3:picasso-compose:3.0.0-alpha05 (substitui com.squareup.picasso:picasso)

## Melhorias ou mudanças em relação as lives

### Sem JetPack Compose (Branch without_compose)

- Uso Gradle com Kotlin ao invés do Groove por recomendação do Android Studio
- Uso do Kotlin ao invés do Java
- Uso do View Binding no RecyclerView Adapter
- Adicionado uma camada de repository para dependências das viewmodels
- Uso de funções assíncronas e coroutines do Kotlin
- ~~Uso do ViewModelProvider.Factory para instanciar o viewmodel com parâmetros~~ (substituído pelo Hilt)
- Mudando o ícone do favoritar quando é favorito ao invés de mudar a cor
- Quando busca todas as notícias o repositório já retorna se uma notícia é ou não uma favorita
- Uso do Hilt para injeção de dependências

### Com JetPack Compose (Branch main)

- Migração para Jetpack Compose:
    - Adicionar dependências do Jetpack Compose
    - Criar news item composable
    - Criar news list composable
    - Criar news screen composable
    - Usar news screen in news fragment mantendo snackbar de erros no fragment
    - Criar favorites screen composable
    - Usar favorites screen in favorites fragment mantendo snackbar de erros no fragment
    - Deletar adapter e fragments layout
    - Adicionar dependências do Navigation Compose e Hilt Navigation Compose
    - Criar app navigation bar (bottom nav bar)
    - Criar app nav host
    - Criar main compose
    - Usar main compose in main activity
    - Remover legacy action bar nos themes.xml
    - Deletar xml da activity main layout, xml do bottom nav menu e xml do mobile navigation
    - Deletar fragments
    - Implementar snackbar de erros com composable
    - Criar app theme composable // TODO
    - Usar app theme in main activity // TODO
    - // TODO

## Rodar

### Requisitos

- Android Studio
- Dispositivo Android Virtual

### Comandos

- Android Studio / Menu / Run / Run 'app'

## Links para referências

- https://github.com/nglauber/books_jetpack
- https://developer.android.com/develop/ui/compose/migrate?hl=pt-br
- https://developer.android.com/develop/ui/compose/setup?hl=pt-br#setup-compose
- https://developer.android.com/develop/ui/compose/migrate/migration-scenarios/recycler-view?hl=pt-br
- https://developer.android.com/develop/ui/compose/migrate/migration-scenarios/navigation?hl=pt-br
- https://developer.android.com/develop/ui/compose/libraries?hl=pt-br#hilt
