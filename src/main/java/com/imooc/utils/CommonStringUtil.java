package com.imooc.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author Calvin
 * @Date 2019-7-20 21:50
 * @Version 1.0
 **/
public class CommonStringUtil {

    private CommonStringUtil() {}

    public static String toLowerCamelName(String name) {
        StringBuffer res = new StringBuffer();
        String[] str = name.split("_");
        int i = 0;

        for(int len = str.length; i < len; ++i) {
            if (i == 0) {
                res.append(str[i].toLowerCase());
            } else {
                res.append(org.springframework.util.StringUtils.capitalize(str[i]));
            }
        }

        return res.toString();
    }

    public static String toUpperCamelName(String name) {
        StringBuffer res = new StringBuffer();
        String[] str = name.split("_");
        int i = 0;

        for(int len = str.length; i < len; ++i) {
            res.append(org.springframework.util.StringUtils.capitalize(str[i]));
        }

        return res.toString();
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    static boolean isEmpty(String str) {
        return str == null || str.length() < 1;
    }

    public static boolean isAllEmpty(String[] args) {
        if (args == null) {
            return true;
        } else {
            String[] var1 = args;
            int var2 = args.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                String arg = var1[var3];
                if (!isEmpty(arg)) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean hasOneEmpty(String[] args) {
        if (args == null) {
            return false;
        } else {
            String[] var1 = args;
            int var2 = args.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                String arg = var1[var3];
                if (isEmpty(arg)) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean isTrimEmpty(String str) {
        return isEmpty(str) || str.trim().length() < 1;
    }

    public static boolean isNotEmpty(Object[] aars) {
        return !isEmpty(aars);
    }

    public static boolean isEmpty(Object[] arrs) {
        return arrs == null || arrs.length < 1;
    }

    public static boolean isNotEmpty(Collection colls) {
        return !isEmpty(colls);
    }

    public static boolean isEmpty(Collection cells) {
        return cells == null || cells.isEmpty();
    }

    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    public static boolean isEqual(Object obj1, Object obj2) {
        if (obj1 != null) {
            return obj2 != null ? obj1.equals(obj2) : false;
        } else {
            return obj2 == null;
        }
    }

    public static boolean isNotEqual(Object obj1, Object obj2) {
        return !isEqual(obj1, obj2);
    }

    public static boolean isEmpty(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    public static String camelToUnderline(String param) {
        if (isEmpty(param)) {
            return "";
        } else {
            int len = param.length();
            StringBuilder sb = new StringBuilder(len);

            for(int i = 0; i < len; ++i) {
                char c = param.charAt(i);
                if (Character.isUpperCase(c) && i > 0) {
                    sb.append('_');
                }

                sb.append(Character.toLowerCase(c));
            }

            return sb.toString();
        }
    }

    public static String underlineToCamel(String param) {
        if (isEmpty(param)) {
            return "";
        } else {
            String temp = param.toLowerCase();
            int len = temp.length();
            StringBuilder sb = new StringBuilder(len);

            for(int i = 0; i < len; ++i) {
                char c = temp.charAt(i);
                if (c == '_') {
                    ++i;
                    if (i < len) {
                        sb.append(Character.toUpperCase(temp.charAt(i)));
                    }
                } else {
                    sb.append(c);
                }
            }

            return sb.toString();
        }
    }

    public static String firstToLowerCase(String param) {
        if (isEmpty(param)) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder(param.length());
            sb.append(param.substring(0, 1).toLowerCase());
            sb.append(param.substring(1));
            return sb.toString();
        }
    }

    public static boolean isUpperCase(String str) {
        return match("^[A-Z]+$", str);
    }

    public static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static String concatCapitalize(String concatStr, String str) {
        if (isEmpty(concatStr)) {
            concatStr = "";
        }

        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            char firstChar = str.charAt(0);
            if (Character.isTitleCase(firstChar)) {
                return str;
            } else {
                StringBuilder sb = new StringBuilder(strLen);
                sb.append(concatStr);
                sb.append(Character.toTitleCase(firstChar));
                sb.append(str.substring(1));
                return sb.toString();
            }
        } else {
            return str;
        }
    }

    public static String capitalize(String str) {
        return concatCapitalize((String)null, str);
    }

    public static boolean checkValNotNull(Object object) {
        if (object instanceof CharSequence) {
            return isNotEmpty((CharSequence)object);
        } else {
            return object != null;
        }
    }

    public static boolean checkValNull(Object object) {
        return !checkValNotNull(object);
    }

    public static boolean containsUpperCase(String word) {
        for(int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            if (Character.isUpperCase(c)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isCapitalMode(String word) {
        return null == word ? false : word.matches("^[0-9A-Z/_]+$");
    }

    public static boolean endsWith(String str, String suffix) {
        return endsWith(str, suffix, false);
    }

    public static boolean endsWithIgnoreCase(String str, String suffix) {
        return endsWith(str, suffix, true);
    }

    private static boolean endsWith(String str, String suffix, boolean ignoreCase) {
        if (str != null && suffix != null) {
            if (suffix.length() > str.length()) {
                return false;
            } else {
                int strOffset = str.length() - suffix.length();
                return str.regionMatches(ignoreCase, strOffset, suffix, 0, suffix.length());
            }
        } else {
            return str == null && suffix == null;
        }
    }

    public static String[] split(String str, String separatorChars) {
        List<String> strings = splitWorker(str, separatorChars, -1, false);
        return (String[])strings.toArray(new String[strings.size()]);
    }

    public static List<String> splitWorker(String str, String separatorChars, int max, boolean preserveAllTokens) {
        if (str == null) {
            return null;
        } else {
            int len = str.length();
            if (len == 0) {
                return Collections.emptyList();
            } else {
                List<String> list = new ArrayList();
                int sizePlus1 = 1;
                int i = 0;
                int start = 0;
                boolean match = false;
                boolean lastMatch = false;
                if (separatorChars != null) {
                    if (separatorChars.length() != 1) {
                        label87:
                        while(true) {
                            while(true) {
                                if (i >= len) {
                                    break label87;
                                }

                                if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                                    if (match || preserveAllTokens) {
                                        lastMatch = true;
                                        if (sizePlus1++ == max) {
                                            i = len;
                                            lastMatch = false;
                                        }

                                        list.add(str.substring(start, i));
                                        match = false;
                                    }

                                    ++i;
                                    start = i;
                                } else {
                                    lastMatch = false;
                                    match = true;
                                    ++i;
                                }
                            }
                        }
                    } else {
                        char sep = separatorChars.charAt(0);

                        label71:
                        while(true) {
                            while(true) {
                                if (i >= len) {
                                    break label71;
                                }

                                if (str.charAt(i) == sep) {
                                    if (match || preserveAllTokens) {
                                        lastMatch = true;
                                        if (sizePlus1++ == max) {
                                            i = len;
                                            lastMatch = false;
                                        }

                                        list.add(str.substring(start, i));
                                        match = false;
                                    }

                                    ++i;
                                    start = i;
                                } else {
                                    lastMatch = false;
                                    match = true;
                                    ++i;
                                }
                            }
                        }
                    }
                } else {
                    label103:
                    while(true) {
                        while(true) {
                            if (i >= len) {
                                break label103;
                            }

                            if (Character.isWhitespace(str.charAt(i))) {
                                if (match || preserveAllTokens) {
                                    lastMatch = true;
                                    if (sizePlus1++ == max) {
                                        i = len;
                                        lastMatch = false;
                                    }

                                    list.add(str.substring(start, i));
                                    match = false;
                                }

                                ++i;
                                start = i;
                            } else {
                                lastMatch = false;
                                match = true;
                                ++i;
                            }
                        }
                    }
                }

                if (match || preserveAllTokens && lastMatch) {
                    list.add(str.substring(start, i));
                }

                return list;
            }
        }
    }

    public static Boolean isCharSequence(Class<?> cls) {
        return cls != null ? CharSequence.class.isAssignableFrom(cls) : false;
    }

    public static Boolean isCharSequence(String propertyType) {
        Class cls = null;

        try {
            cls = Class.forName(propertyType);
        } catch (ClassNotFoundException var3) {
            ;
        }

        return isCharSequence(cls);
    }
}
