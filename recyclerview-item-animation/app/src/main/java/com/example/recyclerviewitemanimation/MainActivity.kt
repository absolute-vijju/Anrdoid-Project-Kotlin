package com.example.recyclerviewitemanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_main)

        val data = getData()

        val adapter = DataAdapter(this, data)
        rvQuotes.layoutManager = LinearLayoutManager(this)
        rvQuotes.adapter = adapter

    }

    private fun getData(): ArrayList<DataModel> {
        val al = arrayListOf<DataModel>()
        al.add(
            DataModel(
                "https://media.gettyimages.com/photos/former-president-apj-abdul-kalam-during-the-91st-birth-anniversary-of-picture-id482182196?s=2048x2048",
                "Life",
                "Climbing to the top demands strength, whether it is to the top of Mount Everest or to the top of your career.Dream is not that which you see while sleeping it is something that does not let you sleep",
                "- A.P.J. Abdul Kalam"
            )
        )
        al.add(
            DataModel(
                "https://media.gettyimages.com/photos/former-president-apj-abdul-kalam-during-the-91st-birth-anniversary-of-picture-id482182196?s=2048x2048",
                "Life",
                "It Is Very Easy To Defeat Someone, But It Is Very Hard To Win Someone",
                "- A.P.J. Abdul Kalam"
            )
        )
        al.add(
            DataModel(
                "https://media.gettyimages.com/photos/former-president-apj-abdul-kalam-during-the-91st-birth-anniversary-of-picture-id482182196?s=2048x2048",
                "Life",
                "Dream is not that which you see while sleeping it is something that does not let you sleep",
                "- A.P.J. Abdul Kalam"
            )
        )
        al.add(
            DataModel(
                "https://media.gettyimages.com/photos/former-president-apj-abdul-kalam-during-the-91st-birth-anniversary-of-picture-id482182196?s=2048x2048",
                "Life",
                "Don't take rest after your first victory because if you fail in second, more lips are waiting to say that your first victory was just luck.",
                "- A.P.J. Abdul Kalam"
            )
        )
        al.add(
            DataModel(
                "https://media.gettyimages.com/photos/former-president-apj-abdul-kalam-during-the-91st-birth-anniversary-of-picture-id482182196?s=2048x2048",
                "Life",
                "Dream is not that which you see while sleeping it is something that does not let you sleep",
                "- A.P.J. Abdul Kalam"
            )
        )
        al.add(
            DataModel(
                "https://media.gettyimages.com/photos/former-president-apj-abdul-kalam-during-the-91st-birth-anniversary-of-picture-id482182196?s=2048x2048",
                "Life",
                "Dream is not that which you see while sleeping it is something that does not let you sleep",
                "- A.P.J. Abdul Kalam"
            )
        )
        al.add(
            DataModel(
                "https://media.gettyimages.com/photos/former-president-apj-abdul-kalam-during-the-91st-birth-anniversary-of-picture-id482182196?s=2048x2048",
                "Life",
                "Dream is not that which you see while sleeping it is something that does not let you sleep",
                "- A.P.J. Abdul Kalam"
            )
        )
        al.add(
            DataModel(
                "https://media.gettyimages.com/photos/former-president-apj-abdul-kalam-during-the-91st-birth-anniversary-of-picture-id482182196?s=2048x2048",
                "Life",
                "Dream is not that which you see while sleeping it is something that does not let you sleep",
                "- A.P.J. Abdul Kalam"
            )
        )
        al.add(
            DataModel(
                "https://media.gettyimages.com/photos/former-president-apj-abdul-kalam-during-the-91st-birth-anniversary-of-picture-id482182196?s=2048x2048",
                "Life",
                "Dream is not that which you see while sleeping it is something that does not let you sleep",
                "- A.P.J. Abdul Kalam"
            )
        )
        al.add(
            DataModel(
                "https://media.gettyimages.com/photos/former-president-apj-abdul-kalam-during-the-91st-birth-anniversary-of-picture-id482182196?s=2048x2048",
                "Life",
                "Dream is not that which you see while sleeping it is something that does not let you sleep",
                "- A.P.J. Abdul Kalam"
            )
        )
        al.add(
            DataModel(
                "https://media.gettyimages.com/photos/former-president-apj-abdul-kalam-during-the-91st-birth-anniversary-of-picture-id482182196?s=2048x2048",
                "Life",
                "Climbing to the top demands strength, whether it is to the top of Mount Everest or to the top of your career.Dream is not that which you see while sleeping it is something that does not let you sleep",
                "- A.P.J. Abdul Kalam"
            )
        )
        al.add(
            DataModel(
                "https://media.gettyimages.com/photos/former-president-apj-abdul-kalam-during-the-91st-birth-anniversary-of-picture-id482182196?s=2048x2048",
                "Life",
                "It Is Very Easy To Defeat Someone, But It Is Very Hard To Win Someone",
                "- A.P.J. Abdul Kalam"
            )
        )
        al.add(
            DataModel(
                "https://media.gettyimages.com/photos/former-president-apj-abdul-kalam-during-the-91st-birth-anniversary-of-picture-id482182196?s=2048x2048",
                "Life",
                "Dream is not that which you see while sleeping it is something that does not let you sleep",
                "- A.P.J. Abdul Kalam"
            )
        )
        al.add(
            DataModel(
                "https://media.gettyimages.com/photos/former-president-apj-abdul-kalam-during-the-91st-birth-anniversary-of-picture-id482182196?s=2048x2048",
                "Life",
                "Don't take rest after your first victory because if you fail in second, more lips are waiting to say that your first victory was just luck.",
                "- A.P.J. Abdul Kalam"
            )
        )
        al.add(
            DataModel(
                "https://media.gettyimages.com/photos/former-president-apj-abdul-kalam-during-the-91st-birth-anniversary-of-picture-id482182196?s=2048x2048",
                "Life",
                "Dream is not that which you see while sleeping it is something that does not let you sleep",
                "- A.P.J. Abdul Kalam"
            )
        )
        al.add(
            DataModel(
                "https://media.gettyimages.com/photos/former-president-apj-abdul-kalam-during-the-91st-birth-anniversary-of-picture-id482182196?s=2048x2048",
                "Life",
                "Dream is not that which you see while sleeping it is something that does not let you sleep",
                "- A.P.J. Abdul Kalam"
            )
        )
        al.add(
            DataModel(
                "https://media.gettyimages.com/photos/former-president-apj-abdul-kalam-during-the-91st-birth-anniversary-of-picture-id482182196?s=2048x2048",
                "Life",
                "Dream is not that which you see while sleeping it is something that does not let you sleep",
                "- A.P.J. Abdul Kalam"
            )
        )
        al.add(
            DataModel(
                "https://media.gettyimages.com/photos/former-president-apj-abdul-kalam-during-the-91st-birth-anniversary-of-picture-id482182196?s=2048x2048",
                "Life",
                "Dream is not that which you see while sleeping it is something that does not let you sleep",
                "- A.P.J. Abdul Kalam"
            )
        )
        al.add(
            DataModel(
                "https://media.gettyimages.com/photos/former-president-apj-abdul-kalam-during-the-91st-birth-anniversary-of-picture-id482182196?s=2048x2048",
                "Life",
                "Dream is not that which you see while sleeping it is something that does not let you sleep",
                "- A.P.J. Abdul Kalam"
            )
        )
        al.add(
            DataModel(
                "https://media.gettyimages.com/photos/former-president-apj-abdul-kalam-during-the-91st-birth-anniversary-of-picture-id482182196?s=2048x2048",
                "Life",
                "Dream is not that which you see while sleeping it is something that does not let you sleep",
                "- A.P.J. Abdul Kalam"
            )
        )
        return al
    }
}
