package com.worksap.stm2016.api.util;

import lombok.Getter;

@Getter
public class JsonResponse {
   private final ResponseStatus response;
   private final String detail;
   
   public JsonResponse(final ResponseStatus r, final String d){
      this.response = r;
      this.detail = d;
   }
   
   public static enum ResponseStatus {
      OK, WARN, ERROR;
   }

   public static JsonResponse deletionResponse(Long id, Long result) {
      if(result == 0) {
         return new JsonResponse(JsonResponse.ResponseStatus.OK, "Deleted");
      } else if (result == -1) {
         return new JsonResponse(JsonResponse.ResponseStatus.ERROR, "Item of id " + id + "does not exists.");
      } else {
         return new JsonResponse(JsonResponse.ResponseStatus.ERROR, "Item of id " + result + " is referenced and cannot be deleted");
      }
   }
}
