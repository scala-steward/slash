package ai.dragonfly.math.visualization

import ai.dragonfly.math.*
import Random.*
import example.Demonstrable
import vector.*
import geometry.Line
import visualization.ConsoleImage.{BLACK, GRAY, WHITE, colorBytes}

object ConsoleImage extends Demonstrable {

/*
⣉̀ 	⣉́ 	⣉̂ 	⣉̃ 	⣉̄ 	⣉̅ 	⣉̆ 	⣉̇ 	⣉̈ 	⣉̉ 	⣉̊ 	⣉̋ 	⣉̌ 	⣉̍ 	⣉̎ 	⣉̏
⣉̐ 	⣉̑ 	⣉̒ 	⣉̓ 	⣉̔ 	⣉̕ 	⣉̖ 	⣉̗ 	⣉̘ 	⣉̙ 	⣉̚ 	⣉̛ 	⣉̜ 	⣉̝ 	⣉̞ 	⣉̟
⣉̠ 	⣉̡ 	⣉̢ 	⣉̣ 	⣉̤ 	⣉̥ 	⣉̦ 	⣉̧ 	⣉̨ 	⣉̩ 	⣉̪ 	⣉̫ 	⣉̬ 	⣉̭ 	⣉̮ 	⣉̯
⣉̰ 	⣉̱ 	⣉̲ 	⣉̳ 	⣉̴ 	⣉̵ 	⣉̶ 	⣉̷ 	⣉̸ 	⣉̹ 	⣉̺ 	⣉̻ 	⣉̼ 	⣉̽ 	⣉̾ 	⣉̿
⣉̀ 	⣉́ 	⣉͂ 	⣉̓ 	⣉̈́ 	⣉ͅ 	⣉͆ 	⣉͇ 	⣉͈ 	⣉͉ 	⣉͊ 	⣉͋ 	⣉͌ 	⣉͍ 	⣉͎
⣉͐ 	⣉͑ 	⣉͒ 	⣉͓ 	⣉͔ 	⣉͕ 	⣉͖ 	⣉͗ 	⣉͘ 	⣉͙ 	⣉͚ 	⣉͛ 	⣉͜⣉ 	⣉͝⣉ 	⣉͞⣉ 	⣉͟⣉
⣉͠⣉ 	⣉͡⣉ 	⣉͢⣉ 	⣉ͣ 	⣉ͤ 	⣉ͥ 	⣉ͦ 	⣉ͧ 	⣉ͨ 	⣉ͩ 	⣉ͪ 	⣉ͫ 	⣉ͬ 	⣉ͭ
⣉᷀ 	⣉᷁ 	⣉᷂ 	⣉᷃ 	⣉᷄ 	⣉᷅ 	⣉᷆ 	⣉᷇ 	⣉᷈ 	⣉᷉ 	⣉᷊ 	⣉᷋ 	⣉᷌ 	⣉᷍ 	⣉᷎ 	⣉᷏
⣉⃐ 	⣉⃑ 	⣉⃒ 	⣉⃓ 	⣉⃔ 	⣉⃕ 	⣉⃖ 	⣉⃗ 	⣉⃘ 	⣉⃙ 	⣉⃚ 	⣉⃛ 	⣉⃜ 	⣉⃝ 	⣉⃞ 	⣉⃟
⣉⃠ 	⣉⃡ 	⣉⃢ 	⣉⃣ 	⣉⃤ 	⣉⃥ 	⣉⃦ 	⣉⃧ 	⣉ 	⣉⃩ 	⣉⃪ 	⣉⃫ 	⣉⃬ 	⣉⃭ 	⣉⃮ 	⣉⃯
⣉⃰ 	⣉ᷓ 	⣉ᷔ 	⣉ᷕ 	⣉ᷖ 	⣉ᷗ 	⣉ᷘ 	⣉ᷙ 	⣉ᷚ 	⣉ᷛ 	⣉ᷜ 	⣉ᷝ 	⣉ᷞ 	⣉ᷟ  	⣉ͮ 	⣉ͯ
⣉ᷠ 	⣉ᷡ 	⣉ᷢ 	⣉ᷣ 	⣉ᷤ 	⣉ᷥ 	⣉ᷦ 	⣉᷼ 	⣉᷽ 	⣉᷾ 	⣉᷿
⣉︠ 	⣉︡ 	⣉︢ 	⣉︣ 	⣉︤ 	⣉︥ 	⣉︦

ͯ⣉ͯ

⃐ 	⃑ 	⃒ 	⃓ 	⃔ 	⃕ 	⃖ 	⃗ 	⃘ 	⃙ 	⃚ 	⃛ 	⃜ 	⃝ 	⃞ 	⃟   ͯ
⠀⃡ 	⠀᷃ 	⠀᷄ 	⠀᷅ 	⠀᷆ 	⠀᷇ 	⠀᷈ 	⠀᷉ 	⠀᷋ 	⠀᷌ 	⠀᷎ 	⠀⃐ 	⠀⃑ 	⠀⃔ 	⠀⃕ 	⠀⃖ 	⠀⃗
⠀͋ 	⠀͌ 	⠀᷀ 	⠀᷁ 	⠀⃛ 	⠀⃜ 	⡇︦ 	⠀ᷠ 	⠀ᷡ 	⠀ᷢ 	⠀ᷣ 	⠀ᷤ 	⠀ᷥ 	⠀ᷦ 	⠀᷾ 	⠀︠ 	⠀︡ 	⠀︢ 	⠀︣ 	⠀︤ 	⡇︥
⠀⃩ 	⠀⃰ 	⠀ᷓ 	⠀ᷔ 	⠀ᷕ 	⠀ᷖ 	⠀ᷗ 	⠀ᷘ 	⠀ᷙ 	⠀ᷚ 	⠀ᷛ 	⠀ᷜ 	⡇ᷝ 	⠀ᷞ 	⠀ᷟ
⠀ͮ 	⠀ͯ 	⠀͐ 	⠀͑ 	⠀͒ 	⠀͗ 	⠀͛ 	⠀ͣ 	⠀ͤ 	⠀ͥ 	⠀̀ 	⠀́ 	⠀͂ 	⠀̈́ 	⠀͆ 	⠀͊ 	⠀ͦ 	⠀ͧ 	⠀ͨ 	⠀ͩ 	⠀ͪ 	⠀ͫ 	⠀ͬ 	⠀ͭ
⠀⃪ 	⠀⃘ 	⠀⃙ 	⠀⃚
⠀͓ 	⠀͔ 	⡇͕ 	⠀͖ 	⡇͙ 	⠀͚ 	⠀᷼ 	⠀᷽ 	⠀᷿

⠒⃞ 	⠒⃟ 	⠒⃠ 	⠒⃢ 	⠒⃣ 	⠒⃤
⠒⃫ 	⠒⃥ 	⠒⃦ 	⠒⃧

 ̀́

*/

  val layerGlyphs:Array[String] = Array[String](
    //  "᷎", "᷀", "ᷘ", "͐᷾", "͒", "́̀", "͚͚ͣ", "͂", "ͦͦͦͦ", "ͨͨͨͨͨ", "᷃",
    "⃘", "⃟", "⃞", "⃠"
  )

  val brailleBytes:Array[String] = Array[String](
    "⠀", "⠁", "⠂", "⠃", "⠄", "⠅", "⠆", "⠇", "⡀", "⡁", "⡂", "⡃", "⡄", "⡅", "⡆", "⡇", "⠈", "⠉", "⠊", "⠋", "⠌", "⠍", "⠎", "⠏", "⡈", "⡉", "⡊", "⡋", "⡌", "⡍", "⡎", "⡏", "⠐", "⠑", "⠒", "⠓", "⠔", "⠕", "⠖", "⠗", "⡐", "⡑", "⡒", "⡓", "⡔", "⡕", "⡖", "⡗", "⠘", "⠙", "⠚", "⠛", "⠜", "⠝", "⠞", "⠟", "⡘", "⡙", "⡚", "⡛", "⡜", "⡝", "⡞", "⡟", "⠠", "⠡", "⠢", "⠣", "⠤", "⠥", "⠦", "⠧", "⡠", "⡡", "⡢", "⡣", "⡤", "⡥", "⡦", "⡧", "⠨", "⠩", "⠪", "⠫", "⠬", "⠭", "⠮", "⠯", "⡨", "⡩", "⡪", "⡫", "⡬", "⡭", "⡮", "⡯", "⠰", "⠱", "⠲", "⠳", "⠴", "⠵", "⠶", "⠷", "⡰", "⡱", "⡲", "⡳", "⡴", "⡵", "⡶", "⡷", "⠸", "⠹", "⠺", "⠻", "⠼", "⠽", "⠾", "⠿", "⡸", "⡹", "⡺", "⡻", "⡼", "⡽", "⡾", "⡿", "⢀", "⢁", "⢂", "⢃", "⢄", "⢅", "⢆", "⢇", "⣀", "⣁", "⣂", "⣃", "⣄", "⣅", "⣆", "⣇", "⢈", "⢉", "⢊", "⢋", "⢌", "⢍", "⢎", "⢏", "⣈", "⣉", "⣊", "⣋", "⣌", "⣍", "⣎", "⣏", "⢐", "⢑", "⢒", "⢓", "⢔", "⢕", "⢖", "⢗", "⣐", "⣑", "⣒", "⣓", "⣔", "⣕", "⣖", "⣗", "⢘", "⢙", "⢚", "⢛", "⢜", "⢝", "⢞", "⢟", "⣘", "⣙", "⣚", "⣛", "⣜", "⣝", "⣞", "⣟", "⢠", "⢠", "⢢", "⢣", "⢤", "⢥", "⢦", "⢧", "⣠", "⣡", "⣢", "⣣", "⣤", "⣥", "⣦", "⣧", "⢨", "⢩", "⢪", "⢫", "⢬", "⢭", "⢮", "⢯", "⣨", "⣩", "⣪", "⣫", "⣬", "⣭", "⣮", "⣯", "⢰", "⢱", "⢲", "⢳", "⢴", "⢵", "⢶", "⢷", "⣰", "⣱", "⣲", "⣳", "⣴", "⣵", "⣶", "⣷", "⢸", "⢹", "⢺", "⢻", "⢼", "⢽", "⢾", "⢿", "⣸", "⣹", "⣺", "⣻", "⣼", "⣽", "⣾", "⣿",
  )

  // Red + Green = Yellow
  // Red + Blue = Magenta
  // Green + Blue = Cyan
  // Red + Green + Blue = White

  val BLACK:Int = 0
  val RED:Int = 1
  val GREEN:Int = 2
  val YELLOW:Int = 3
  val BLUE:Int = 4
  val MAGENTA:Int = 5
  val CYAN:Int = 6
  val WHITE:Int = 7
  val GRAY:Int = 8

  val colorBytes:Array[String] = Array[String](
    Console.BLACK,
    Console.RED,
    Console.GREEN,
    Console.YELLOW,
    Console.BLUE,
    Console.MAGENTA,
    Console.CYAN,
    Console.RESET,
    Console.WHITE
  )

  def randomSpiral(ci:ConsoleImage, color:Int):ConsoleImage = {
    val p:Vector2 = defaultRandom.next[Vector2](Vector2(ci.width, ci.height))
    val v:Vector2 = Vector2(1.0, 0.0)
    val s = 1.002 + (Math.random() * 0.0002)
    val dT:Double = (Math.random() - 0.5) * Math.PI / 100.0
    var pV:Vector2 = v.copy()
    val end = squareInPlace(ci.width) + squareInPlace(ci.height)
    while (v.euclideanNormSquared < end){
      pV = p + v
      v.rotate(dT).scale(s)
      Line.trace2D(
        pV,
        p + v,
        (dX:Int, dY:Int) => {
          if (dX >= 0 && dX < ci.width) {
            if (dY >= 0 && dY < ci.height) {
              ci.setPixel(dX, dY, color)
            }
          }
        }
      )
    }
    ci
  }

  override def demo(implicit sb:StringBuilder = new StringBuilder()):StringBuilder = {

    val ci: ConsoleImage = new ConsoleImage(40, 200)
    for (i <- 0 until 7) {
      randomSpiral(ci, 1 << (i % 3))
    }
    sb.append(ci)

    sb
  }

  override def name: String = "ConsoleImage"

}

class ConsoleImage(val width:Int, val height:Int) {

  import Console.{BOLD, RESET}

  private val w:Int = width >> 1   // width / 2
  private val h:Int = height >> 2  // height / 4

  val pixelCount:Int = w * (h + 1)

  inline def linearIndexOf(x: Int, y: Int): Int = {
    var out = -1
    if (x > -1) {  // && y > -1  // left out y positive check because negative y will always cause negative output
      val x0 = x >> 1
      if (x0 < w) {
        val y0 = y >> 2
        if (y0 < h ) out = x0 + (y0 * w)
      }
    }
    out
  }

  val pixels:Array[Int] = Array.fill(pixelCount)(0)

  val layer:Array[String] = Array.fill(pixelCount)("")

  def setPixel(x:Int, y:Int, color:Int):ConsoleImage = {
    val i:Int = linearIndexOf(x, y)
    if (i > -1) {
      pixels(i) = pixels(i) | ((color << 16) | ((1 << (y % 4)) << (x % 2 * 4)))
      // erasing? (if (color == K) pixels(i) & ~byte else pixels(i) | byte)
    }
    this
  }

  def setGlyph(x:Int, y:Int, glyph:String, color:Int):ConsoleImage = {
    val i:Int = linearIndexOf(x, y)
    if (i > -1) {
      layer(i) = layer(i) + glyph
      pixels(i) = pixels(i) | (color << 16)
    }
    this
  }

  def lines:Array[String] = {
    val out:Array[String] = new Array[String](h)
    for (y <- 0 until h) {
      var lastColor:Int = 0
      val sb:StringBuilder = StringBuilder()
      for (x <- 0 until w) {
        val bytes = pixels((y * w) + x)
        var color = bytes >> 16
        if (color > WHITE) {
          color = color & WHITE
        }
        if (color < 1) color = ConsoleImage.colorBytes.length - 1
        val pattern = bytes & 0x000000ff
        if (color == lastColor || color == 0) {
          sb.append(ConsoleImage.brailleBytes(pattern))
        } else {
          lastColor = color
          sb.append(ConsoleImage.colorBytes(color))
          sb.append(ConsoleImage.brailleBytes(pattern))
        }
        sb.append(layer((y * w) + x))
      }
      out(y) = sb.append(RESET).toString()
    }
    out
  }

  override def toString:String = {
    val sb:StringBuilder = StringBuilder()
    val ls:Array[String] = lines
    for (line <- ls) {
      sb.append(line).append("\n")
    }
    sb.toString()
  }
}

/* Unicode Braille Characters for Plotting:

⠁̇ ⠈⠂⠐᛫·𝆺𝆹⠄⠠.⡀⢀̣ [ ⃘][̊ ][⠀ͦ]
⚀⚁⚂⚃⚄⚅
🁣🁤🁥🁦🁧🁨🁩
🁪🁫🁬🁭🁮🁯🁰
🁱🁲🁳🁴🁵🁶🁷
🁸🁹🁺🁻🁼🁽🁾
🁿🂀🂁🂂🂃🂄🂅
🂆🂇🂈🂉🂊🂋🂌
🂍🂎🂏🂐🂑🂒🂓
🁢

🀱🀲🀳🀴🀵🀶🀷
🀸🀹🀺🀻🀼🀽🀾
🀿🁀🁁🁂🁃🁄🁅
🁆🁇🁈🁉🁊🁋🁌
🁍🁎🁏🁐🁑🁒🁓
🁔🁕🁖🁗🁘🁙🁚
🁛🁜🁝🁞🁟🁠🁡
🀰

⠀⠈⠐⠘⠠⠨⠰⠸⢀⢈⢐⢘⢠⢨⢰⢸
⠁⠉⠑⠙⠡⠩⠱⠹⢁⢉⢑⢙⢠⢩⢱⢹
⠂⠊⠒⠚⠢⠪⠲⠺⢂⢊⢒⢚⢢⢪⢲⢺
⠃⠋⠓⠛⠣⠫⠳⠻⢃⢋⢓⢛⢣⢫⢳⢻
⠄⠌⠔⠜⠤⠬⠴⠼⢄⢌⢔⢜⢤⢬⢴⢼
⠅⠍⠕⠝⠥⠭⠵⠽⢅⢍⢕⢝⢥⢭⢵⢽
⠆⠎⠖⠞⠦⠮⠶⠾⢆⢎⢖⢞⢦⢮⢶⢾
⠇⠏⠗⠟⠧⠯⠷⠿⢇⢏⢗⢟⢧⢯⢷⢿
⡀⡈⡐⡘⡠⡨⡰⡸⣀⣈⣐⣘⣠⣨⣰⣸
⡁⡉⡑⡙⡡⡩⡱⡹⣁⣉⣑⣙⣡⣩⣱⣹
⡂⡊⡒⡚⡢⡪⡲⡺⣂⣊⣒⣚⣢⣪⣲⣺
⡃⡋⡓⡛⡣⡫⡳⡻⣃⣋⣓⣛⣣⣫⣳⣻
⡄⡌⡔⡜⡤⡬⡴⡼⣄⣌⣔⣜⣤⣬⣴⣼
⡅⡍⡕⡝⡥⡭⡵⡽⣅⣍⣕⣝⣥⣭⣵⣽
⡆⡎⡖⡞⡦⡮⡶⡾⣆⣎⣖⣞⣦⣮⣶⣾
⡇⡏⡗⡟⡧⡯⡷⡿⣇⣏⣗⣟⣧⣯⣷⣿

⠠
⠰
⢰or⠸
⢸
▕
▕·
▕⠆
▕⠇or⡆
▕⡇
▕▍
⣿︲⣿̣̇
⣿￤⣿
⣿︰⣿
⣿︙⣿
⣿⦙⣿
⃛⣿▕⣿
⣿▐⣿
⣿⋮⣿
⣿⁝⣿
⣿⁞⣿
⣿:⣿
⣿¦⣿
⣿┆⣿
⣿┊⣿
⣿╎⣿
⣿╏⣿
⣿┋⣿
⣿⁞⣿
⣿┆⣿⃓
⣿┊⣿
⣿‖⣿
⦀ ⦙
⣿·⣿:⣿︙⣿∵⣿∴⣿⃞
⢸⦙·⢸:⢸︙⢸∵⢸∴⢸
⢸⦙⡇·⡇:⡇︙̣᛬︙⡇∵⡇∴⡇
· :  ∵ ̣∴
︰ ︙ ̇᛬̣᛬
⋮ ⁝ ⁞ ◌
︲ ⁖ ⁘ ⁙ ⁛
… …⃛ ⦙⦙⦙⃨⦙⃒⃨⃜ [⦙⦙⦙⃨⦙]⃒
⋮⦙⃨⃛


̇̇̇̇

 ⁖ ⁘ ⁙ ⁛
◌

🁢🂠 🀆 🀫 🀕 🀘 🀞 🀠 🀡
𝄖 𝄗 𝄘 𝄙 𝄚 𝄛

⚊⚋𝌀

⚌⚍⚎
⚏𝌁𝌂
𝌃𝌄𝌅

☰ ☱ ☲ ☳
☴ ☵ ☶ ☷

䷀䷁䷂䷃䷄䷅䷆䷇
䷈䷉䷊䷋䷌䷍䷎䷏
䷐䷑䷒䷓䷔䷕䷖䷗
䷘䷙䷚䷛䷜䷝䷞䷟
䷠䷡䷢䷣䷤䷥䷦䷧
䷨䷩䷪䷫䷬䷭䷮䷯
䷰䷱䷲䷳䷴䷵䷶䷷
䷸䷹䷺䷻䷼䷽䷾䷿

𝌆𝌇𝌈𝌉𝌊𝌋𝌌𝌍𝌎𝌏𝌐𝌑𝌒𝌓𝌔𝌕𝌖𝌗𝌘𝌙
𝌚𝌛𝌜𝌝𝌞𝌟𝌠𝌡𝌢𝌣𝌤𝌥𝌦𝌧𝌨𝌩𝌪𝌫𝌬𝌭
𝌮𝌯𝌰𝌱𝌲𝌳𝌴𝌵𝌶𝌷𝌸𝌹𝌺𝌻𝌼𝌽𝌾𝌿𝍀𝍁
𝍂𝍃𝍄𝍅𝍆𝍇𝍈𝍉𝍊𝍋𝍌𝍍𝍎𝍏𝍐𝍑𝍒𝍓𝍔𝍕𝍖

░ ▒ ▓ █
◌
 ⃘
 ⃙
 ⃚
⦿ ◉ ⦾ ◌ ◎ ☉ ⃝ • ◦ 𝇇 𝇈 •⃢  ◦⃢  •⃣  ◦⃣
☀ ☼ ⚹ ⊕
☾☽ 🌑 🌒 🌓 🌔 🌕 🌖 🌗 🌘
 ̇ ̣ ᛫
•̣· ̣̇•

 ̣̇   ⃘

▤ ▥ ▦ ▧ ▨ ▩
⚄
x̣
⣿̀ 	⣿́ 	⣿̂ 	⣿̃ 	⣿̄ 	⣿̅ 	⣿̆ 	⣿̇ 	⣿̈ 	⣿̉ 	⣿̊ 	⣿̋ 	⣿̌ 	⣿̍ 	⣿̎ 	⣿̏
⣿̐ 	⣿̑ 	⣿̒ 	⣿̓ 	⣿̔ 	⣿̕ 	⣿̖ 	⣿̗ 	⣿̘ 	⣿̙ 	⣿̚ 	⣿̛ 	⣿̜ 	⣿̝ 	⣿̞ 	⣿̟
⣿̠ 	⣿̡ 	⣿̢ 	⣿̣ 	⣿̤ 	⣿̥ 	⣿̦ 	⣿̧ 	⣿̨ 	⣿̩ 	⣿̪ 	⣿̫ 	⣿̬ 	⣿̭ 	⣿̮ 	⣿̯
⣿̰ 	⣿̱ 	⣿̲ 	⣿̳ 	⣿̴ 	⣿̵ 	⣿̶ 	⣿̷ 	⣿̸ 	⣿̹ 	⣿̺ 	⣿̻ 	⣿̼ 	⣿̽ 	⣿̾ 	⣿̿
⣿̀ 	⣿́ 	⣿͂ 	⣿̓ 	⣿̈́ 	⣿ͅ 	⣿͆ 	⣿͇ 	⣿͈ 	⣿͉ 	⣿͊ 	⣿͋ 	⣿͌ 	⣿͍ 	⣿͎
⣿͐ 	⣿͑ 	⣿͒ 	⣿͓ 	⣿͔ 	⣿͕ 	⣿͖ 	⣿͗ 	⣿͘ 	⣿͙ 	⣿͚ 	⣿͛ 	⣿͜⣿ 	⣿͝⣿ 	⣿͞⣿ 	⣿͟⣿̅
 	⣿͠⣿ 	⣿͡⣿ 	⣿͢⣿ 	⣿ͣ 	⣿ͤ 	⣿ͥ 	⣿ͦ 	⣿ͧ 	⣿ͨ 	⣿ͩ 	⣿ͪ 	⣿ͫ 	⣿ͬ 	⣿ͭ
⣿᷀ 	⣿᷁ 	⣿᷂ 	⣿᷃ 	⣿᷄ 	⣿᷅ 	⣿᷆ 	⣿᷇ 	⣿᷈ 	⣿᷉ 	⣿᷊ 	⣿᷋ 	⣿᷌ 	⣿᷍ 	⣿᷎ 	⣿᷏
⣿⃐ 	⣿⃑ 	⣿⃒ 	⣿⃓ 	⣿⃔ 	⣿⃕ 	⣿⃖ 	⣿⃗ 	⣿⃘ 	⣿⃙ 	⣿⃚ 	⣿⃛ 	⣿⃜ 	⣿⃝ 	⣿⃞ 	⣿⃟
⣿⃠ 	⣿⃡ 	⣿⃢ 	⣿⃣ 	⣿⃤ 	⣿⃥ 	⣿⃦ 	⣿⃧ 	⣿ 	⣿⃩ 	⣿⃪ 	⣿⃫ 	⣿⃬ 	⣿⃭ 	⣿⃮ 	⣿⃯
⣿⃰ 	⣿ᷓ 	⣿ᷔ 	⣿ᷕ 	⣿ᷖ 	⣿ᷗ 	⣿ᷘ 	⣿ᷙ 	⣿ᷚ 	⣿ᷛ 	⣿ᷜ 	⣿ᷝ 	⣿ᷞ 	⣿ᷟ  	⣿ͮ 	⣿ͯ
⣿ᷠ 	⣿ᷡ 	⣿ᷢ 	⣿ᷣ 	⣿ᷤ 	⣿ᷥ 	⣿ᷦ 	⣿᷼ 	⣿᷽ 	⣿᷾ 	⣿᷿
⣿︠ 	⣿︡ 	⣿︢ 	⣿︣ 	⣿︤ 	⣿︥ 	⣿︦

Combining Char᷍acters:
⠀⃐ 	⠀⃑ 	⠀⃒ 	⠀⃓ 	⠀⃔ 	⠀⃕ 	⠀⃖ 	⠀⃗ 	⠀⃘ 	⠀⃙ 	⠀⃚ 	⠀⃛ 	⠀⃜ 	⠀⃝ 	⠀⃞ 	⠀⃟
⠀⃠ 	⠀⃡ 	⠀⃢ 	⠀⃣ 	⠀⃤ 	⠀⃥ 	⠀⃦ 	⠀⃧ 	⠀⃨ 	⠀⃩ 	⠀⃪ 	⠀⃫ 	⠀⃬ 	⠀⃭ 	⠀⃮ 	⠀⃯
⠀⃰
⦾
*/


//  val braille:immutable.TreeMap[Int, String] = immutable.TreeMap[Int, String](
//    0 -> "⠀", 0 + (1 << 4) -> "⠈", 0 + (2 << 4) -> "⠐", 0 + (3 << 4) -> "⠘", 0 + (4 << 4) -> "⠠", 0 + (5 << 4) -> "⠨", 0 + (6 << 4) -> "⠰", 0 + (7 << 4) -> "⠸", 0 + (8 << 4) -> "⢀", 0 + (9 << 4) -> "⢈", 0 + (10 << 4) -> "⢐", 0 + (11 << 4) -> "⢘", 0 + (12 << 4) -> "⢠", 0 + (13 << 4) -> "⢨", 0 + (14 << 4) -> "⢰", 0 + (15 << 4) -> "⢸",
//    1 -> "⠁", 1 + (1 << 4) -> "⠉", 1 + (2 << 4) -> "⠑", 1 + (3 << 4) -> "⠙", 1 + (4 << 4) -> "⠡", 1 + (5 << 4) -> "⠩", 1 + (6 << 4) -> "⠱", 1 + (7 << 4) -> "⠹", 1 + (8 << 4) -> "⢁", 1 + (9 << 4) -> "⢉", 1 + (10 << 4) -> "⢑", 1 + (11 << 4) -> "⢙", 1 + (12 << 4) -> "⢠", 1 + (13 << 4) -> "⢩", 1 + (14 << 4) -> "⢱", 1 + (15 << 4) -> "⢹",
//    2 -> "⠂", 2 + (1 << 4) -> "⠊", 2 + (2 << 4) -> "⠒", 2 + (3 << 4) -> "⠚", 2 + (4 << 4) -> "⠢", 2 + (5 << 4) -> "⠪", 2 + (6 << 4) -> "⠲", 2 + (7 << 4) -> "⠺", 2 + (8 << 4) -> "⢂", 2 + (9 << 4) -> "⢊", 2 + (10 << 4) -> "⢒", 2 + (11 << 4) -> "⢚", 2 + (12 << 4) -> "⢢", 2 + (13 << 4) -> "⢪", 2 + (14 << 4) -> "⢲", 2 + (15 << 4) -> "⢺",
//    3 -> "⠃", 3 + (1 << 4) -> "⠋", 3 + (2 << 4) -> "⠓", 3 + (3 << 4) -> "⠛", 3 + (4 << 4) -> "⠣", 3 + (5 << 4) -> "⠫", 3 + (6 << 4) -> "⠳", 3 + (7 << 4) -> "⠻", 3 + (8 << 4) -> "⢃", 3 + (9 << 4) -> "⢋", 3 + (10 << 4) -> "⢓", 3 + (11 << 4) -> "⢛", 3 + (12 << 4) -> "⢣", 3 + (13 << 4) -> "⢫", 3 + (14 << 4) -> "⢳", 3 + (15 << 4) -> "⢻",
//    4 -> "⠄", 4 + (1 << 4) -> "⠌", 4 + (2 << 4) -> "⠔", 4 + (3 << 4) -> "⠜", 4 + (4 << 4) -> "⠤", 4 + (5 << 4) -> "⠬", 4 + (6 << 4) -> "⠴", 4 + (7 << 4) -> "⠼", 4 + (8 << 4) -> "⢄", 4 + (9 << 4) -> "⢌", 4 + (10 << 4) -> "⢔", 4 + (11 << 4) -> "⢜", 4 + (12 << 4) -> "⢤", 4 + (13 << 4) -> "⢬", 4 + (14 << 4) -> "⢴", 4 + (15 << 4) -> "⢼",
//    5 -> "⠅", 5 + (1 << 4) -> "⠍", 5 + (2 << 4) -> "⠕", 5 + (3 << 4) -> "⠝", 5 + (4 << 4) -> "⠥", 5 + (5 << 4) -> "⠭", 5 + (6 << 4) -> "⠵", 5 + (7 << 4) -> "⠽", 5 + (8 << 4) -> "⢅", 5 + (9 << 4) -> "⢍", 5 + (10 << 4) -> "⢕", 5 + (11 << 4) -> "⢝", 5 + (12 << 4) -> "⢥", 5 + (13 << 4) -> "⢭", 5 + (14 << 4) -> "⢵", 5 + (15 << 4) -> "⢽",
//    6 -> "⠆", 6 + (1 << 4) -> "⠎", 6 + (2 << 4) -> "⠖", 6 + (3 << 4) -> "⠞", 6 + (4 << 4) -> "⠦", 6 + (5 << 4) -> "⠮", 6 + (6 << 4) -> "⠶", 6 + (7 << 4) -> "⠾", 6 + (8 << 4) -> "⢆", 6 + (9 << 4) -> "⢎", 6 + (10 << 4) -> "⢖", 6 + (11 << 4) -> "⢞", 6 + (12 << 4) -> "⢦", 6 + (13 << 4) -> "⢮", 6 + (14 << 4) -> "⢶", 6 + (15 << 4) -> "⢾",
//    7 -> "⠇", 7 + (1 << 4) -> "⠏", 7 + (2 << 4) -> "⠗", 7 + (3 << 4) -> "⠟", 7 + (4 << 4) -> "⠧", 7 + (5 << 4) -> "⠯", 7 + (6 << 4) -> "⠷", 7 + (7 << 4) -> "⠿", 7 + (8 << 4) -> "⢇", 7 + (9 << 4) -> "⢏", 7 + (10 << 4) -> "⢗", 7 + (11 << 4) -> "⢟", 7 + (12 << 4) -> "⢧", 7 + (13 << 4) -> "⢯", 7 + (14 << 4) -> "⢷", 7 + (15 << 4) -> "⢿",
//    8 -> "⡀", 8 + (1 << 4) -> "⡈", 8 + (2 << 4) -> "⡐", 8 + (3 << 4) -> "⡘", 8 + (4 << 4) -> "⡠", 8 + (5 << 4) -> "⡨", 8 + (6 << 4) -> "⡰", 8 + (7 << 4) -> "⡸", 8 + (8 << 4) -> "⣀", 8 + (9 << 4) -> "⣈", 8 + (10 << 4) -> "⣐", 8 + (11 << 4) -> "⣘", 8 + (12 << 4) -> "⣠", 8 + (13 << 4) -> "⣨", 8 + (14 << 4) -> "⣰", 8 + (15 << 4) -> "⣸",
//    9 -> "⡁", 9 + (1 << 4) -> "⡉", 9 + (2 << 4) -> "⡑", 9 + (3 << 4) -> "⡙", 9 + (4 << 4) -> "⡡", 9 + (5 << 4) -> "⡩", 9 + (6 << 4) -> "⡱", 9 + (7 << 4) -> "⡹", 9 + (8 << 4) -> "⣁", 9 + (9 << 4) -> "⣉", 9 + (10 << 4) -> "⣑", 9 + (11 << 4) -> "⣙", 9 + (12 << 4) -> "⣡", 9 + (13 << 4) -> "⣩", 9 + (14 << 4) -> "⣱", 9 + (15 << 4) -> "⣹",
//    10 -> "⡂", 10 + (1 << 4) -> "⡊", 10 + (2 << 4) -> "⡒", 10 + (3 << 4) -> "⡚", 10 + (4 << 4) -> "⡢", 10 + (5 << 4) -> "⡪", 10 + (6 << 4) -> "⡲", 10 + (7 << 4) -> "⡺", 10 + (8 << 4) -> "⣂", 10 + (9 << 4) -> "⣊", 10 + (10 << 4) -> "⣒", 10 + (11 << 4) -> "⣚", 10 + (12 << 4) -> "⣢", 10 + (13 << 4) -> "⣪", 10 + (14 << 4) -> "⣲", 10 + (15 << 4) -> "⣺",
//    11 -> "⡃", 11 + (1 << 4) -> "⡋", 11 + (2 << 4) -> "⡓", 11 + (3 << 4) -> "⡛", 11 + (4 << 4) -> "⡣", 11 + (5 << 4) -> "⡫", 11 + (6 << 4) -> "⡳", 11 + (7 << 4) -> "⡻", 11 + (8 << 4) -> "⣃", 11 + (9 << 4) -> "⣋", 11 + (10 << 4) -> "⣓", 11 + (11 << 4) -> "⣛", 11 + (12 << 4) -> "⣣", 11 + (13 << 4) -> "⣫", 11 + (14 << 4) -> "⣳", 11 + (15 << 4) -> "⣻",
//    12 -> "⡄", 12 + (1 << 4) -> "⡌", 12 + (2 << 4) -> "⡔", 12 + (3 << 4) -> "⡜", 12 + (4 << 4) -> "⡤", 12 + (5 << 4) -> "⡬", 12 + (6 << 4) -> "⡴", 12 + (7 << 4) -> "⡼", 12 + (8 << 4) -> "⣄", 12 + (9 << 4) -> "⣌", 12 + (10 << 4) -> "⣔", 12 + (11 << 4) -> "⣜", 12 + (12 << 4) -> "⣤", 12 + (13 << 4) -> "⣬", 12 + (14 << 4) -> "⣴", 12 + (15 << 4) -> "⣼",
//    13 -> "⡅", 13 + (1 << 4) -> "⡍", 13 + (2 << 4) -> "⡕", 13 + (3 << 4) -> "⡝", 13 + (4 << 4) -> "⡥", 13 + (5 << 4) -> "⡭", 13 + (6 << 4) -> "⡵", 13 + (7 << 4) -> "⡽", 13 + (8 << 4) -> "⣅", 13 + (9 << 4) -> "⣍", 13 + (10 << 4) -> "⣕", 13 + (11 << 4) -> "⣝", 13 + (12 << 4) -> "⣥", 13 + (13 << 4) -> "⣭", 13 + (14 << 4) -> "⣵", 13 + (15 << 4) -> "⣽",
//    14 -> "⡆", 14 + (1 << 4) -> "⡎", 14 + (2 << 4) -> "⡖", 14 + (3 << 4) -> "⡞", 14 + (4 << 4) -> "⡦", 14 + (5 << 4) -> "⡮", 14 + (6 << 4) -> "⡶", 14 + (7 << 4) -> "⡾", 14 + (8 << 4) -> "⣆", 14 + (9 << 4) -> "⣎", 14 + (10 << 4) -> "⣖", 14 + (11 << 4) -> "⣞", 14 + (12 << 4) -> "⣦", 14 + (13 << 4) -> "⣮", 14 + (14 << 4) -> "⣶", 14 + (15 << 4) -> "⣾",
//    15 -> "⡇", 15 + (1 << 4) -> "⡏", 15 + (2 << 4) -> "⡗", 15 + (3 << 4) -> "⡟", 15 + (4 << 4) -> "⡧", 15 + (5 << 4) -> "⡯", 15 + (6 << 4) -> "⡷", 15 + (7 << 4) -> "⡿", 15 + (8 << 4) -> "⣇", 15 + (9 << 4) -> "⣏", 15 + (10 << 4) -> "⣗", 15 + (11 << 4) -> "⣟", 15 + (12 << 4) -> "⣧", 15 + (13 << 4) -> "⣯", 15 + (14 << 4) -> "⣷", 15 + (15 << 4) -> "⣿"
//  )
//
//  for ((k, v) <- braille) print(s"\"$v\", ")