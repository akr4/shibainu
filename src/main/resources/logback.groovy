import static ch.qos.logback.classic.Level.*
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.classic.encoder.PatternLayoutEncoder

appender("STDOUT", ConsoleAppender) {
  encoder(PatternLayoutEncoder) {
      pattern = "%date{HH:mm:ss.SSS} %-5level %logger{15} %message%n"
  }
}
root(INFO, [ "STDOUT" ])
logger("net.physalis", DEBUG, [ "STDOUT" ], false)
logger("org.springframework", INFO, [ "STDOUT" ], false)
logger("org.springframework.cache", DEBUG, [ "STDOUT" ], false)
logger("org.eclipse.jetty", INFO, [ "STDOUT" ], false)
logger("com.mysema.query.sql.AbstractSQLQuery", DEBUG, [ "STDOUT" ], false)
