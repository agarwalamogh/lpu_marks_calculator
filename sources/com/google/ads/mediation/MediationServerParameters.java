package com.google.ads.mediation;

import com.google.android.gms.internal.zzqf;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@Deprecated
public abstract class MediationServerParameters {

    public static final class MappingException extends Exception {
        public MappingException(String str) {
            super(str);
        }
    }

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    protected @interface Parameter {
        String name();

        boolean required() default true;
    }

    public void load(Map<String, String> map) throws MappingException {
        Field[] fields;
        HashMap hashMap = new HashMap();
        for (Field field : getClass().getFields()) {
            Parameter parameter = (Parameter) field.getAnnotation(Parameter.class);
            if (parameter != null) {
                hashMap.put(parameter.name(), field);
            }
        }
        if (hashMap.isEmpty()) {
            zzqf.zzbh("No server options fields detected. To suppress this message either add a field with the @Parameter annotation, or override the load() method.");
        }
        for (Entry entry : map.entrySet()) {
            Field field2 = (Field) hashMap.remove(entry.getKey());
            if (field2 != null) {
                try {
                    field2.set(this, entry.getValue());
                } catch (IllegalAccessException e) {
                    String str = (String) entry.getKey();
                    zzqf.zzbh(new StringBuilder(String.valueOf(str).length() + 49).append("Server option \"").append(str).append("\" could not be set: Illegal Access").toString());
                } catch (IllegalArgumentException e2) {
                    String str2 = (String) entry.getKey();
                    zzqf.zzbh(new StringBuilder(String.valueOf(str2).length() + 43).append("Server option \"").append(str2).append("\" could not be set: Bad Type").toString());
                }
            } else {
                String str3 = (String) entry.getKey();
                String str4 = (String) entry.getValue();
                zzqf.zzbf(new StringBuilder(String.valueOf(str3).length() + 31 + String.valueOf(str4).length()).append("Unexpected server option: ").append(str3).append(" = \"").append(str4).append("\"").toString());
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Field field3 : hashMap.values()) {
            if (((Parameter) field3.getAnnotation(Parameter.class)).required()) {
                String str5 = "Required server option missing: ";
                String valueOf = String.valueOf(((Parameter) field3.getAnnotation(Parameter.class)).name());
                zzqf.zzbh(valueOf.length() != 0 ? str5.concat(valueOf) : new String(str5));
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(((Parameter) field3.getAnnotation(Parameter.class)).name());
            }
        }
        if (sb.length() > 0) {
            String str6 = "Required server option(s) missing: ";
            String valueOf2 = String.valueOf(sb.toString());
            throw new MappingException(valueOf2.length() != 0 ? str6.concat(valueOf2) : new String(str6));
        }
    }
}
