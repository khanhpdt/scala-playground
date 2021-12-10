import java.time.{LocalDateTime, ZoneOffset}
import java.time.format.DateTimeFormatter

val dtf = DateTimeFormatter.ISO_INSTANT
dtf.format(LocalDateTime.now().toInstant(ZoneOffset.UTC))