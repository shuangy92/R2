package com.worksap.stm2016.api.util;

import lombok.Getter;

import java.util.List;

@Getter
public class JsonArrayResponse {
   private final long total;
   private final List rows;

   public JsonArrayResponse(final List rows, final long total){
      this.rows = rows;
      this.total = total;
   }
}
