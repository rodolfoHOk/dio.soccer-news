package br.com.dio.soccernews.ui.components

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.dio.soccernews.R
import br.com.dio.soccernews.domain.model.News
import com.squareup.picasso3.Picasso
import com.squareup.picasso3.compose.rememberPainter

@Composable
fun NewsItem(news: News, onFavorite: (news: News) -> Unit) {
    val picasso = Picasso.Builder(LocalContext.current).build()
    val painter = picasso.rememberPainter(key = news.id) { picasso ->
        picasso.load(news.image)
            .placeholder(R.drawable.ic_soccer)
            .error(R.drawable.ic_error_outline)
            .centerCrop()
            .resize(396, 160)

    }

    val context = LocalContext.current

    fun handleOpenLink() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(news.link))
        context.startActivity(intent)
    }

    fun handleShare() {
        val intent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, news.link)
        val shareString = context.getString(R.string.share)
        context.startActivity(Intent.createChooser(intent, shareString))
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            painter = painter,
            contentDescription = stringResource(id = R.string.card_image_description),
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(color = MaterialTheme.colorScheme.onBackground)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(text = news.title, style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = news.headline)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = { handleOpenLink() }) {
                Text(text = stringResource(id = R.string.card_btn_open_link_label))
            }

            Row {
                IconButton(onClick = { onFavorite(news) }) {
                    if (news.favorite) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_favorite),
                            contentDescription = stringResource(
                                id = R.string.card_favorite_btn_description
                            ),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_favorite_border),
                            contentDescription = stringResource(
                                id = R.string.card_favorite_btn_description
                            ),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                IconButton(onClick = { handleShare() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_share),
                        contentDescription = stringResource(
                            id = R.string.card_favorite_btn_description
                        ),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun NewsItemPreview() {
    val news = News(
        id = 1,
        title = "Você sabia?",
        headline = "Mulheres colecionam conquistas históricas no futebol; veja oito delas neste 8 de março.",
        image = "https://rodolfohok.github.io/dio.soccer-news-api/images/1.jpg",
        link = "https://ge.globo.com/pe/futebol/noticia/2022/03/08/voce-sabia-mulheres-colecionam-conquistas-historicas-no-futebol-veja-oito-delas-neste-8-de-marco.ghtml",
        favorite = false
    )

    NewsItem(news = news, onFavorite = { newsClicked ->
        Log.d("App -> ", "favorite click on news with id: ${newsClicked.id}")
    })
}
