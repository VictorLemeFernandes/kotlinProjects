package com.victor.superhero

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.victor.superhero.model.Superhero
import com.victor.superhero.ui.theme.SuperheroesTheme

@Composable
fun HeroItem(hero: Superhero, modifier: Modifier = Modifier) {
    Card (
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(minHeight = 72.dp)
                .padding(16.dp)
        ) {
            /*Esse Modifier.weight(1f) me deu uma dor de cabeça... Aparentemente ele só faz sentido estando aqui no escopo da Row, na Column dentro da HeroInformation ele não rola...*/
            HeroInformation(hero.nameRes, hero.descriptionRes, Modifier.weight(1f))
            Spacer(Modifier.width(16.dp))
            HeroIcon(hero.imageRes)
        }
    }
}

@Composable
fun HeroInformation(
    @StringRes heroName: Int,
    @StringRes heroDescription: Int,
    modifier: Modifier = Modifier
) {
    Column (modifier = modifier) {
        Text(
            text = stringResource(heroName),
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = stringResource(heroDescription),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun HeroIcon(@DrawableRes heroIcon: Int, modifier: Modifier = Modifier) {
    Box (modifier = modifier
        .height(dimensionResource(R.dimen.image_size))
        .clip(MaterialTheme.shapes.small)
    ) {
        Image(
            painter = painterResource(heroIcon),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeroPreview() {
    SuperheroesTheme (darkTheme = true) {
        HeroItem(
            hero = Superhero(
                nameRes = R.string.hero4,
                descriptionRes = R.string.description4,
                imageRes = R.drawable.android_superhero4
            )
        )
    }
}
