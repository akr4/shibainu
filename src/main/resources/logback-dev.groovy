import static ch.qos.logback.classic.Level.*
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.classic.encoder.PatternLayoutEncoder

def highlight = System.getProperty("log.highlight") == "true" ?: false

appender("STDOUT", ConsoleAppender) {
  withJansi = true
  encoder(PatternLayoutEncoder) {
      pattern = (highlight) ?
        "%white(%date{HH:mm:ss.SSS}) %highlight(%-5level) %magenta(%X{requestId}) %cyan(%20.30logger{30}) %highlight(%message) %n" :
        "%date{HH:mm:ss.SSS} %-5level %X{requestId} %20.30logger{30} %message%n"
  }
}
root(INFO, [ "STDOUT" ])
logger("net.physalis", DEBUG, [ "STDOUT" ], false)
logger("org.springframework", DEBUG, [ "STDOUT" ], false)
logger("org.springframework.cache", DEBUG, [ "STDOUT" ], false)
logger("org.eclipse.jetty", INFO, [ "STDOUT" ], false)
logger("com.mysema.query.sql.AbstractSQLQuery", DEBUG, [ "STDOUT" ], false)
