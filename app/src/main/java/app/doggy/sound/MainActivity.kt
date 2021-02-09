package app.doggy.sound

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //音声ファイルの配列。
    private val sounds: Array<Int> = arrayOf(
            R.raw.vinyl,
            R.raw.hairdryer,
            R.raw.vacuum,
            R.raw.water
    )

    //画像の配列。
    private val images: Array<Int> = arrayOf(
            R.drawable.vinyl,
            R.drawable.hairdryer,
            R.drawable.vacuum,
            R.drawable.water
    )

    //暗い画像の配列。
    private val darkImages: Array<Int> = arrayOf(
            R.drawable.vinyl_dark,
            R.drawable.hairdryer_dark,
            R.drawable.vacuum_dark,
            R.drawable.water_dark
    )

    //再生状況を管理する配列。
    var isPlaying: Array<Int?> = arrayOfNulls(sounds.size)

    //MediaPlayerを格納する配列。
    private val mediaPlayers: Array<MediaPlayer?> = arrayOfNulls(sounds.size)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //配列に値を代入。
        for (i in mediaPlayers.indices) {

            //再生状況を代入。
            isPlaying[i] = 0

            //MediaPlayerをインスタンス化。
            mediaPlayers[i] = MediaPlayer.create(applicationContext, sounds[i])
            //ループ再生の設定。
            mediaPlayers[i]?.isLooping = true
        }

        //クリックリスナを設定。
        vinylImage.setOnTouchListener { view, event ->

            if (event.action == MotionEvent.ACTION_DOWN) {
                vinylImage.setImageResource(darkImages[0])

            } else if(event.action == MotionEvent.ACTION_UP) {
                soundPlay(0)
                vinylImage.setImageResource(images[0])

            }
            true
        }

        hairdryerImage.setOnTouchListener { view, event ->

            if (event.action == MotionEvent.ACTION_DOWN) {
                hairdryerImage.setImageResource(darkImages[1])

            } else if(event.action == MotionEvent.ACTION_UP) {
                soundPlay(1)
                hairdryerImage.setImageResource(images[1])

            }
            true
        }

        vacuumImage.setOnTouchListener { view, event ->

            if (event.action == MotionEvent.ACTION_DOWN) {
                vacuumImage.setImageResource(darkImages[2])

            } else if(event.action == MotionEvent.ACTION_UP) {
                soundPlay(2)
                vacuumImage.setImageResource(images[2])

            }
            true
        }

        waterImage.setOnTouchListener { view, event ->

            if (event.action == MotionEvent.ACTION_DOWN) {
                waterImage.setImageResource(darkImages[3])

            } else if(event.action == MotionEvent.ACTION_UP) {
                soundPlay(3)
                waterImage.setImageResource(images[3])

            }
            true
        }

    }

    //音声を再生する処理。
    fun soundPlay(soundNum: Int) {

        //他の音声を止める処理。
        for (i in isPlaying.indices) {

            //例外処理。
            if (i == soundNum) {
                continue
            }

            when(isPlaying[i]) {
                1 -> {
                    //音声を停止。
                    mediaPlayers[i]?.pause()

                    //再生状況を更新
                    isPlaying[i] = 0

                    Log.d("playing", "$i stop")
                }
            }
        }

        //再生状況によって処理を分岐。
        when(isPlaying[soundNum]) {
            //音声が流れていない時。
            0 -> {
                //音声を再生。
                mediaPlayers[soundNum]?.seekTo(0)
                mediaPlayers[soundNum]?.start()

                //再生状況を更新
                isPlaying[soundNum] = 1

                Log.d("playing", "$soundNum start")
            }

            //音声が流れている時。
            1 -> {
                //音声を停止。
                mediaPlayers[soundNum]?.pause()

                //再生状況を更新
                isPlaying[soundNum] = 0

                Log.d("playing", "$soundNum stop")
            }
        }
    }
}