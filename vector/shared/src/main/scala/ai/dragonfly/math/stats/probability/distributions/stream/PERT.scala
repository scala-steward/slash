package ai.dragonfly.math.stats.probability.distributions.stream

import ai.dragonfly.math.stats.probability.distributions
import ai.dragonfly.math.stats.probability.distributions.ProbabilityDistribution
import ai.dragonfly.math.example.{Demonstrable, OnlineProbDistDemo}
import ai.dragonfly.math.stats.BoundedMean

object PERT {
  val doNotUse:String = "" +
    "As a special case of the Beta distribution, PERT only has utility in applications with unknowable σ².\n" +
    "In such situations, PERT approximates σ² with the huristic value: σ² = ((μ - min) * (MAX - μ)) / 7.0.\n" +
    "Because stream.Beta can approximate σ² directly, its accuracy always meets or exceeds that of streem.PERT.\n\n" +
    "One should always prefer stream.Beta, but if you insist on using stream.PERT, the UseBetaDistributionInstead\n" +
    "exception generated by its constructor contains a reference to a functioning instance of stream.PERT:\n\n" +
    "val onlinePERT = try {\n" +
      "\tnew ai.dragonfly.math.stats.probability.distributions.stream.PERT\n" +
    "} catch {\n" +
      "\t/* I understand the superiority of stream.Beta over stream.PERT, but I have reasons! */\n" +
      "\tcase ai.dragonfly.math.stats.probability.distributions.stream.UseBetaDistributionInstead(pert) => pert\n" +
    "}"

  val demo = new Demonstrable {
    val d:Demonstrable = OnlineProbDistDemo[Double, distributions.PERT, PERT](
      "Streaming PERT",
      distributions.PERT(21, 42.0, 69.0),
      /* I understand the superiority of stream.Beta over stream.PERT, but I have reasons! */
      try { new PERT } catch { case UseBetaDistributionInstead(pert) => pert },
      1000
    )
    override def demo(implicit sb: StringBuilder): StringBuilder = {
      sb.append(doNotUse).append("\n")
      d.demo(sb).append("\n")
    }

    override def name: String = "stream.PERT"
  }
}

/**
 * As a special case of the Beta distribution, PERT only has utility in applications with unknowable σ².
 * In such situations, PERT approximates σ² with the huristic value: σ² = ((μ - min) * (MAX - μ)) / 7.0.
 * Because stream.Beta can approximate σ² directly, its accuracy always meets or exceeds that of streem.PERT.

 * One should always prefer stream.Beta, but if you insist on using stream.PERT, the UseBetaDistributionInstead
 * exception generated by its constructor contains a reference to a functioning instance of stream.PERT:
 * val onlinePERT = try {
 * 	new ai.dragonfly.math.stats.probability.distributions.stream.PERT
 * } catch {
 * 	/* I understand the superiority of stream.Beta over stream.PERT, but I have reasons! */
 * 	case ai.dragonfly.math.stats.probability.distributions.stream.UseBetaDistributionInstead(pert) => pert
 * }
 */


class PERT extends OnlineUnivariateProbabilityDistributionEstimator[Double, distributions.PERT] {

  val estimator = new BoundedMeanEstimator[Double](distributions.PERT.domain)

  override def observe(frequency: Double, observation: Double):PERT = {
    estimator.observe(Array[Double](frequency, observation))
    this
  }

  override def estimate: distributions.EstimatedPERT = {
    val bμ̂ = estimator.sampleBoundedMean
    distributions.EstimatedPERT(
      distributions.PERT(bμ̂),
      bμ̂.ℕ̂
    )
  }

  throw new UseBetaDistributionInstead(this)
}

case class UseBetaDistributionInstead(pert:PERT) extends Exception(PERT.doNotUse)