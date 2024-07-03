package com.example.wishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "wish-title")
    val title: String = "",
    @ColumnInfo("wish-desc")
    val description: String = ""
)

object DummyWishes {
    val wishList: List<Wish> = listOf(
        Wish(title = "Buy watch", description = "Buy watch on Sunday, goggle watch"),
        Wish(title="Go on a hike", description="Plan a hike on Saturday, Mount Fuji trail"),
        Wish(title="Read a book", description="Finish reading 'To Kill a Mockingbird' by Harper Lee"),
        Wish(title="Learn guitar", description="Practice guitar chords for 30 minutes daily"),
        Wish(title="Cook dinner", description="Try a new recipe for spaghetti carbonara on Friday"),
        Wish(title="Visit museum", description="Explore the modern art section of the city museum this weekend"),
        Wish(title="Buy shoes", description="Purchase new running shoes at the mall on Wednesday"),
        Wish(title="Start a blog", description="Create and publish the first post on my personal blog"),
        Wish(title="Attend a workshop", description="Sign up for the photography workshop next month"),
        Wish(title="Go to a concert", description="Buy tickets for the rock concert on Friday night"),
        Wish(title="Exercise regularly", description="Go to the gym every morning at 7 AM")

        )
}