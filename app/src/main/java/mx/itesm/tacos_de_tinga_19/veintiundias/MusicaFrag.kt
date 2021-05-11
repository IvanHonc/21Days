package mx.itesm.tacos_de_tinga_19.veintiundias

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import mx.itesm.tacos_de_tinga_19.veintiundias.databinding.FragmentMusicaBinding
import java.util.*

//Autor: Viviana Osorio Nieto
class MusicaFrag: Fragment() {

    private var _binding: FragmentMusicaBinding? = null
    var thiscontext: Context? = null
    //cronometro
    private var START_TIME_IN_MILLIS: Long = 600000 //60000 es 1 minuto
    private var mCountDownTimer: CountDownTimer? = null
    private var mTimerRunning = false
    private var mTimeLeftInMillis = START_TIME_IN_MILLIS

    //musica
    private lateinit var soundPool : SoundPool
    private var rain = 0
    private var fire = 0
    private var water = 0
    private lateinit var mp: MediaPlayer

    private fun configurarCHR(){
        _binding?.btnIniciar?.setOnClickListener {
            if (mTimerRunning) { pausarTimer(); }
            else { iniciarTimer(); }
        }
        _binding?.buttonReset?.setOnClickListener { resetTimer() }
        _binding?.btn5min?.setOnClickListener {
            mTimeLeftInMillis = 300000
            val timeLeftFormatted: String = java.lang.String.format(Locale.getDefault(), "%02d:%02d", 5, 0)
            _binding?.textViewCountdown?.setText(timeLeftFormatted)}
        _binding?.btn10min?.setOnClickListener {
            mTimeLeftInMillis = 600000
            val timeLeftFormatted: String = java.lang.String.format(Locale.getDefault(), "%02d:%02d", 10, 0)
            _binding?.textViewCountdown?.setText(timeLeftFormatted)}
        _binding?.btn20min?.setOnClickListener {
            mTimeLeftInMillis = 1200000
            val timeLeftFormatted: String = java.lang.String.format(Locale.getDefault(), "%02d:%02d", 20, 0)
            _binding?.textViewCountdown?.setText(timeLeftFormatted)}
    }

    private fun resetTimer() {
        START_TIME_IN_MILLIS = 0
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        _binding?.buttonReset?.setVisibility(View.INVISIBLE);
        _binding?.btnIniciar?.setVisibility(View.VISIBLE);
    }
    private fun stopSounds(){
        soundPool.autoPause()
        try {
            if (mp.isPlaying) {
                mp.stop()
                mp.release()
                mp = MediaPlayer.create(context, mx.itesm.tacos_de_tinga_19.veintiundias.R.raw.musicalofi)
                mp.setVolume(.3F,.3F)
            }else{}
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    private fun pausarTimer() {
        mCountDownTimer?.cancel();
        mTimerRunning = false;
        _binding?.btnIniciar?.setImageResource(mx.itesm.tacos_de_tinga_19.veintiundias.R.drawable.smallbtnstart1)
        _binding?.buttonReset?.setVisibility(View.VISIBLE);
        //musica
        stopSounds()
    }

    private fun iniciarTimer() {
        mCountDownTimer = object : CountDownTimer(mTimeLeftInMillis,   1000) {
            override fun onTick(millisUntilFinished: Long) {
                mTimeLeftInMillis = millisUntilFinished
                updateCountDownText()
            }
            override fun onFinish() {
                mTimerRunning = false
                resetTimer()
                stopSounds()
            }
        }.start()
        mTimerRunning = true
        _binding?.btnIniciar?.setImageResource(mx.itesm.tacos_de_tinga_19.veintiundias.R.drawable.btnstop)
        _binding?.buttonReset?.setVisibility(View.INVISIBLE)
        //musica
        var duration = mp.getDuration()
        val random = (0..duration).random()
        try {
            if (mp.isPlaying) { }else{
                mp.stop()
                mp.release()
                mp = MediaPlayer.create(context, mx.itesm.tacos_de_tinga_19.veintiundias.R.raw.musicalofi)
                mp.seekTo(random)
                mp.setVolume(.3F,.3F)
                mp.start()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun updateCountDownText() {
        val minutes = (mTimeLeftInMillis / 1000).toInt() / 60
        val seconds = (mTimeLeftInMillis / 1000).toInt() % 60
        val timeLeftFormatted: String = java.lang.String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
        _binding?.textViewCountdown?.setText(timeLeftFormatted)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = mx.itesm.tacos_de_tinga_19.veintiundias.databinding.FragmentMusicaBinding.bind(view)
        _binding = binding
        configurarCHR()
        configurarMusica()
        configurarGIF()
        configurarGifMusica()
    }

    private fun configurarMusica() {
        mp = MediaPlayer.create(thiscontext, mx.itesm.tacos_de_tinga_19.veintiundias.R.raw.musicalofi)
        mp.setVolume(.3F,.3F)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = mx.itesm.tacos_de_tinga_19.veintiundias.databinding.FragmentMusicaBinding.inflate(inflater, container, false)
        val view = _binding!!.root
        thiscontext = container?.context
        return view
    }
    private fun configurarGIF(){
        _binding?.btnmar?.setOnClickListener{
            _binding?.gifImageView?.setImageResource(mx.itesm.tacos_de_tinga_19.veintiundias.R.drawable.racoonsea)
            musicaGIF(1)
            _binding?.imgBackgrnd?.setImageResource(mx.itesm.tacos_de_tinga_19.veintiundias.R.drawable.fondoagua)
        }
        _binding?.btnbuho?.setOnClickListener{
            _binding?.gifImageView?.setImageResource(mx.itesm.tacos_de_tinga_19.veintiundias.R.drawable.durmiendo)
            musicaGIF(4)
            _binding?.imgBackgrnd?.setImageResource(mx.itesm.tacos_de_tinga_19.veintiundias.R.drawable.fondodurmiendo)
        }
        _binding?.btnfuego?.setOnClickListener{
            _binding?.gifImageView?.setImageResource(mx.itesm.tacos_de_tinga_19.veintiundias.R.drawable.fuego)
            musicaGIF(2)
            _binding?.imgBackgrnd?.setImageResource(mx.itesm.tacos_de_tinga_19.veintiundias.R.drawable.fondofuego)
        }
        _binding?.btnLluvia?.setOnClickListener{
            _binding?.gifImageView?.setImageResource(mx.itesm.tacos_de_tinga_19.veintiundias.R.drawable.gifrain)
            musicaGIF(3)
            _binding?.imgBackgrnd?.setImageResource(mx.itesm.tacos_de_tinga_19.veintiundias.R.drawable.lluviafondo)
        }
    }

    private fun configurarGifMusica() {
        //Aqui se crean 3 audios para los efectos de los gif
        val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        soundPool = SoundPool.Builder()
                .setMaxStreams(2)
                .setAudioAttributes(audioAttributes)
                .build()
        rain = soundPool.load(thiscontext, mx.itesm.tacos_de_tinga_19.veintiundias.R.raw.rain, 1)
        fire = soundPool.load(thiscontext, mx.itesm.tacos_de_tinga_19.veintiundias.R.raw.fuego, 1)
        water = soundPool.load(thiscontext, mx.itesm.tacos_de_tinga_19.veintiundias.R.raw.mar, 1)
    }

    private fun musicaGIF(num: Int) {
        when (num){
            1 -> {//mar
                soundPool.autoPause()
                soundPool.play(water, 150F, 150F, 0, -1, 1F)
            }
            2 -> {//FUEGO
                soundPool.autoPause()
                soundPool.play(fire, 100F, 100F, 0, -1, 1F)
            }
            3 -> {//LLuvia
                soundPool.autoPause()
                soundPool.play(rain, 0.2F, 0.2F, 0, -1, 1F)
            }
            4 -> {//nada
                soundPool.autoPause()
            }
        }
    }
    override fun onStop() {
        super.onStop()
        stopSounds()
        mp.release()
    }

}



