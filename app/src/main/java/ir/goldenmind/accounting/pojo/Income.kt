package ir.goldenmind.accounting.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Income(
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "amount") var amount: Long,
    @ColumnInfo(name = "comment") var comment: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}