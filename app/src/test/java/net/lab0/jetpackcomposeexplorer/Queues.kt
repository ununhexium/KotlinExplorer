package net.lab0.jetpackcomposeexplorer

import org.junit.jupiter.api.Test

class Queues {
  @Test
  fun `queue estimation`() {
    var nameIndex = 'A'
    fun nextName() = (nameIndex++).toString()

    data class Job(val load: Double = 1.0, val name: String = nextName())
    data class Server(val remaining: Double = 0.0, val name: String = nextName())

    data class JobInQueue(val job: Job, val delay: Double)
    data class ServerWithQueue(
        val server: Server,
        val queuedJob: MutableList<JobInQueue> = mutableListOf()
    ) {
      fun delay() = server.remaining + queuedJob.sumByDouble { it.job.load }
    }

    val servers = listOf(Server(), Server(0.4), Server(1.0))
    val jobs = listOf(Job(), Job(), Job(), Job())

    tailrec fun internalEstimateWaitingTime(
        jobsQueue: List<Job>,
        serversWithQueue: List<ServerWithQueue>
    ): List<ServerWithQueue> =
        if (jobsQueue.isEmpty()) {
          serversWithQueue
        } else {
          val shortestDelay = serversWithQueue.minByOrNull { it.delay() }!! // will crash if there is 0 server
          val head = jobsQueue.first()
          val inQueue = JobInQueue(head, shortestDelay.delay() + head.load)
          val tail = jobsQueue.drop(1)
          shortestDelay.queuedJob.add(inQueue)
          internalEstimateWaitingTime(tail, serversWithQueue)
        }

    fun estimateWaitingTime(jobsQueue: List<Job>, hosts: List<Server>) =
        internalEstimateWaitingTime(jobsQueue, hosts.map { ServerWithQueue(it) })

    println("Servers: " + servers)
    println("Jobs: " + jobs)

    println(
        estimateWaitingTime(jobs, servers).joinToString("\n") {
          it.server.name + "(" + it.server.remaining + ")" + " -> " + it.queuedJob
        }
    )

    estimateWaitingTime(jobs, servers).forEach { queue ->
      println("Queue: $queue")
      queue.queuedJob.forEach { job ->
        println(job.job.name + "'s delay is " +  job.delay)
      }
    }
  }
}