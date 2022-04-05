package ai.dragonfly.math

import ai.dragonfly.math.*
import example.*

import scala.language.postfixOps

object Factorial {
  // 0! - 20! Source: https://en.wikipedia.org/wiki/Factorial
  /*
    var bi:BigInt = BigInt(2432902008176640000L) // 20!
    for(i <- 21 to 100) { bi = bi*BigInt(i); println(s"BigInt(\"$bi\"), // $i!") }
  */

  private lazy val table:Array[BigInt] = Array[BigInt](
    BigInt(1), // 0!
    BigInt(1), // 1!
    BigInt(2), // 2!
    BigInt(6), // 3!
    BigInt(24), // 4!
    BigInt(120), // 5!
    BigInt(720), // 6!
    BigInt(5040), // 7!
    BigInt(40320), // 8!
    BigInt(362880), // 9!
    BigInt(3628800), // 10!
    BigInt(39916800), // 11!
    BigInt(479001600), // 12!
    BigInt(6227020800L), // 13!
    BigInt(87178291200L), // 14!
    BigInt(1307674368000L), // 15!
    BigInt(20922789888000L), // 16!
    BigInt(355687428096000L), // 17!
    BigInt(6402373705728000L), // 18!
    BigInt(121645100408832000L), // 19!
    BigInt(2432902008176640000L), // 20!
    BigInt("51090942171709440000"), // 21!
    BigInt("1124000727777607680000"), // 22!
    BigInt("25852016738884976640000"), // 23!
    BigInt("620448401733239439360000"), // 24!
    BigInt("15511210043330985984000000"), // 25!
    BigInt("403291461126605635584000000"), // 26!
    BigInt("10888869450418352160768000000"), // 27!
    BigInt("304888344611713860501504000000"), // 28!
    BigInt("8841761993739701954543616000000"), // 29!
    BigInt("265252859812191058636308480000000"), // 30!
    BigInt("8222838654177922817725562880000000"), // 31!
    BigInt("263130836933693530167218012160000000"), // 32!
    BigInt("8683317618811886495518194401280000000"), // 33!
    BigInt("295232799039604140847618609643520000000"), // 34!
    BigInt("10333147966386144929666651337523200000000"), // 35!
    BigInt("371993326789901217467999448150835200000000"), // 36!
    BigInt("13763753091226345046315979581580902400000000"), // 37!
    BigInt("523022617466601111760007224100074291200000000"), // 38!
    BigInt("20397882081197443358640281739902897356800000000"), // 39!
    BigInt("815915283247897734345611269596115894272000000000"), // 40!
    BigInt("33452526613163807108170062053440751665152000000000"), // 41!
    BigInt("1405006117752879898543142606244511569936384000000000"), // 42!
    BigInt("60415263063373835637355132068513997507264512000000000"), // 43!
    BigInt("2658271574788448768043625811014615890319638528000000000"), // 44!
    BigInt("119622220865480194561963161495657715064383733760000000000"), // 45!
    BigInt("5502622159812088949850305428800254892961651752960000000000"), // 46!
    BigInt("258623241511168180642964355153611979969197632389120000000000"), // 47!
    BigInt("12413915592536072670862289047373375038521486354677760000000000"), // 48!
    BigInt("608281864034267560872252163321295376887552831379210240000000000"), // 49!
    BigInt("30414093201713378043612608166064768844377641568960512000000000000"), // 50!
    BigInt("1551118753287382280224243016469303211063259720016986112000000000000"), // 51!
    BigInt("80658175170943878571660636856403766975289505440883277824000000000000"), // 52!
    BigInt("4274883284060025564298013753389399649690343788366813724672000000000000"), // 53!
    BigInt("230843697339241380472092742683027581083278564571807941132288000000000000"), // 54!
    BigInt("12696403353658275925965100847566516959580321051449436762275840000000000000"), // 55!
    BigInt("710998587804863451854045647463724949736497978881168458687447040000000000000"), // 56!
    BigInt("40526919504877216755680601905432322134980384796226602145184481280000000000000"), // 57!
    BigInt("2350561331282878571829474910515074683828862318181142924420699914240000000000000"), // 58!
    BigInt("138683118545689835737939019720389406345902876772687432540821294940160000000000000"), // 59!
    BigInt("8320987112741390144276341183223364380754172606361245952449277696409600000000000000"), // 60!
    BigInt("507580213877224798800856812176625227226004528988036003099405939480985600000000000000"), // 61!
    BigInt("31469973260387937525653122354950764088012280797258232192163168247821107200000000000000"), // 62!
    BigInt("1982608315404440064116146708361898137544773690227268628106279599612729753600000000000000"), // 63!
    BigInt("126886932185884164103433389335161480802865516174545192198801894375214704230400000000000000"), // 64!
    BigInt("8247650592082470666723170306785496252186258551345437492922123134388955774976000000000000000"), // 65!
    BigInt("544344939077443064003729240247842752644293064388798874532860126869671081148416000000000000000"), // 66!
    BigInt("36471110918188685288249859096605464427167635314049524593701628500267962436943872000000000000000"), // 67!
    BigInt("2480035542436830599600990418569171581047399201355367672371710738018221445712183296000000000000000"), // 68!
    BigInt("171122452428141311372468338881272839092270544893520369393648040923257279754140647424000000000000000"), // 69!
    BigInt("11978571669969891796072783721689098736458938142546425857555362864628009582789845319680000000000000000"), // 70!
    BigInt("850478588567862317521167644239926010288584608120796235886430763388588680378079017697280000000000000000"), // 71!
    BigInt("61234458376886086861524070385274672740778091784697328983823014963978384987221689274204160000000000000000"), // 72!
    BigInt("4470115461512684340891257138125051110076800700282905015819080092370422104067183317016903680000000000000000"), // 73!
    BigInt("330788544151938641225953028221253782145683251820934971170611926835411235700971565459250872320000000000000000"), // 74!
    BigInt("24809140811395398091946477116594033660926243886570122837795894512655842677572867409443815424000000000000000000"), // 75!
    BigInt("1885494701666050254987932260861146558230394535379329335672487982961844043495537923117729972224000000000000000000"), // 76!
    BigInt("145183092028285869634070784086308284983740379224208358846781574688061991349156420080065207861248000000000000000000"), // 77!
    BigInt("11324281178206297831457521158732046228731749579488251990048962825668835325234200766245086213177344000000000000000000"), // 78!
    BigInt("894618213078297528685144171539831652069808216779571907213868063227837990693501860533361810841010176000000000000000000"), // 79!
    BigInt("71569457046263802294811533723186532165584657342365752577109445058227039255480148842668944867280814080000000000000000000"), // 80!
    BigInt("5797126020747367985879734231578109105412357244731625958745865049716390179693892056256184534249745940480000000000000000000"), // 81!
    BigInt("475364333701284174842138206989404946643813294067993328617160934076743994734899148613007131808479167119360000000000000000000"), // 82!
    BigInt("39455239697206586511897471180120610571436503407643446275224357528369751562996629334879591940103770870906880000000000000000000"), // 83!
    BigInt("3314240134565353266999387579130131288000666286242049487118846032383059131291716864129885722968716753156177920000000000000000000"), // 84!
    BigInt("281710411438055027694947944226061159480056634330574206405101912752560026159795933451040286452340924018275123200000000000000000000"), // 85!
    BigInt("24227095383672732381765523203441259715284870552429381750838764496720162249742450276789464634901319465571660595200000000000000000000"), // 86!
    BigInt("2107757298379527717213600518699389595229783738061356212322972511214654115727593174080683423236414793504734471782400000000000000000000"), // 87!
    BigInt("185482642257398439114796845645546284380220968949399346684421580986889562184028199319100141244804501828416633516851200000000000000000000"), // 88!
    BigInt("16507955160908461081216919262453619309839666236496541854913520707833171034378509739399912570787600662729080382999756800000000000000000000"), // 89!
    BigInt("1485715964481761497309522733620825737885569961284688766942216863704985393094065876545992131370884059645617234469978112000000000000000000000"), // 90!
    BigInt("135200152767840296255166568759495142147586866476906677791741734597153670771559994765685283954750449427751168336768008192000000000000000000000"), // 91!
    BigInt("12438414054641307255475324325873553077577991715875414356840239582938137710983519518443046123837041347353107486982656753664000000000000000000000"), // 92!
    BigInt("1156772507081641574759205162306240436214753229576413535186142281213246807121467315215203289516844845303838996289387078090752000000000000000000000"), // 93!
    BigInt("108736615665674308027365285256786601004186803580182872307497374434045199869417927630229109214583415458560865651202385340530688000000000000000000000"), // 94!
    BigInt("10329978488239059262599702099394727095397746340117372869212250571234293987594703124871765375385424468563282236864226607350415360000000000000000000000"), // 95!
    BigInt("991677934870949689209571401541893801158183648651267795444376054838492222809091499987689476037000748982075094738965754305639874560000000000000000000000"), // 96!
    BigInt("96192759682482119853328425949563698712343813919172976158104477319333745612481875498805879175589072651261284189679678167647067832320000000000000000000000"), // 97!
    BigInt("9426890448883247745626185743057242473809693764078951663494238777294707070023223798882976159207729119823605850588608460429412647567360000000000000000000000"), // 98!
    BigInt("933262154439441526816992388562667004907159682643816214685929638952175999932299156089414639761565182862536979208272237582511852109168640000000000000000000000"), // 99!
    BigInt("93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000") // 100!
  )

  /**
   * Computes !, the factorial, of integers.
   *
   * Factorial(x) = x! = x * (x-1) * (x-2) * (...) * 2 * 1
   *
   * Internals:
   *
   * This implementation relies on scala.math.BigInt to avoid the notorious overflow problems of factorial functions; 13! overflows Int and 21! overflows Long.
   *
   * for x! where 0 <= x <= 100, returns a cached value.
   * for x! where x > 100, starts with 100! from the cache and iteratively multiplies 101, 102, ... x.
   *
   * @param x
   * @return x!
   */

  def apply(x:Int):BigInt = if (x < table.length) table(x) else partialFactorial(BigInt(x))

  /**
   * Computes x!, the factorial(x), of Long x.
   *
   * Factorial(x) = x! = x * (x-1) * (x-2) * (...) * 2 * 1
   *
   * Internals:
   *
   * This implementation relies on scala.math.BigInt to avoid the notorious overflow problems of factorial functions; 13! overflows Int and 21! overflows Long.
   *
   * for x! where 0 <= x <= 100, returns a cached value.
   * for x! where x > 100, starts with 100! from the cache and iteratively multiplies 101, 102, ... x.
   *
   * @param x
   * @return x!
   */

  def apply(x:Long):BigInt = if (x < table.length) table(x.toInt) else partialFactorial(BigInt(x))


  /**
   * Computes x!, the factorial(x), of BigInt x.
   *
   * Factorial(x) = x! = x * (x-1) * (x-2) * (...) * 2 * 1
   *
   * Internals:
   *
   * This implementation relies on scala.math.BigInt to avoid the notorious overflow problems of factorial functions; 13! overflows Int and 21! overflows Long.
   *
   * for x! where 0 <= x <= 100, returns a cached value.
   * for x! where x > 100, starts with 100! from the cache and iteratively multiplies 101, 102, ... x.
   *
   * @param x
   * @return x!
   */

  def apply(x:BigInt):BigInt = {
    if (x < table.length) table(x.toInt)
    else partialFactorial(x)
  }

  private def partialFactorial(x: BigInt): BigInt = {
    var out:BigInt = table(table.length - 1)
    for (xi <- BigInt(table.length) to x) {
      out = out * xi
    }
    out
  }
//  case class FactorialParameter(x:Int) {
//    def ! : BigInt = apply(x)
//  }
//
//  given int2FactorialParameter: Conversion[Int, FactorialParameter] =
//    FactorialParameter(_)

  /**
   * Int extension method for ! syntax:
   * Enable postfix ops to support traditional factorial syntax:
   *   3!
   *   val i:Int = 9; i!
   * Warning: Consider order of operations carefully:
   *   10 * (4!) yields 240
   *   10 * (4)! yields 815915283247897734345611269596115894272000000000
   *
   * Without postifx ops:
   *   3.!
   *   val i:Int = 9; i.!
   * @param x
   * @return x!
   */
  extension (x: Int)
    def ! : BigInt = apply(x)

  extension (x: Long)
    def ! : BigInt = apply(x)

  extension (x: BigInt)
    def ! : BigInt = apply(x)


  object demo extends Demonstrable {
    def demo(implicit sb:StringBuilder = new StringBuilder()):StringBuilder = {
      for (x:Int <- Seq(1, 2, 3, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096)) {
        sb.append(s"\t${x}! = ${x!}\n")
      }
      sb
    }
    def name:String = "Factorial"
  }
}


object TestFactorial extends App {
  println(
    Factorial.demo.demo(new StringBuilder()).toString
  )
}