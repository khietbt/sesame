package io.github.khietbt.converters;

import java.util.UUID;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

@ReadingConverter
public class UuidReadingConverter implements Converter<String, UUID> {

  @Override
  public UUID convert(String source) {
    return UUID.fromString(source);
  }
}
