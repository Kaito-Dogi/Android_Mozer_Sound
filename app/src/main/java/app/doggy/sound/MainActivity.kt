package app.doggy.sound

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //音声ファイルの配列。
    private val sounds: Array<Int> = arrayOf(
            R.raw.vinyl,
            R.raw.hairdryer,
            R.raw.vacuum,
            R.raw.water
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
        vinylImage.setOnClickListener {
            soundPlay(0)
        }

        hairdryerImage.setOnClickListener {
            soundPlay(1)
        }

        vacuumImage.setOnClickListener {
            soundPlay(2)
        }

        waterImage.setOnClickListener {
            soundPlay(3)
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