/**
 * Created by izeye on 2014. 11. 20..
 */
class Base64InGroovy {

    static char[] TO_BASE64 = new char[64]
    static byte[] FROM_BASE64 = new byte[128]

    static def PADDING_SYMBOL = '=';
    static def MASK = 0x3F

    static {
        def value = 0
        for (def c = 'A'; c <= 'Z'; c++) {
            TO_BASE64[value] = c
            FROM_BASE64[c] = value++
        }
        for (def c = 'a'; c <= 'z'; c++) {
            TO_BASE64[value] = c
            FROM_BASE64[c] = value++
        }
        for (def c = '0'; c <= '9'; c++) {
            TO_BASE64[value] = c
            FROM_BASE64[c] = value++
        }
        TO_BASE64[value] = '+'
        TO_BASE64['+'] = value++
        TO_BASE64[value] = '/'
        TO_BASE64['/'] = value++
    }


    static def encode(bytes) {
        def sb = new StringBuilder();

        def length = bytes.length
        for (def i = 0; i < length; i += 3) {
            sb.append(TO_BASE64[bytes[i] >>> 2])

            def value = (bytes[i] << 4) & MASK
            if (i + 1 < length) {
                value += bytes[i + 1] >>> 4
            }
            sb.append(TO_BASE64[value])
            if (i + 1 == length) {
                sb.append(PADDING_SYMBOL)
                sb.append(PADDING_SYMBOL)
                break
            }

            value = (bytes[i + 1] << 2) & MASK
            if (i + 2 < length) {
                value += bytes[i + 2] >>> 6
            }
            sb.append(TO_BASE64[value])
            if (i + 2 == length) {
                sb.append(PADDING_SYMBOL)
                break
            }

            sb.append(TO_BASE64[bytes[i + 2] & MASK])
        }
        return sb.toString()
    }

    static def decode(String encoded) {
        def bytes = []

        def length = encoded.length()
        for (def i = 0; i < length; i += 4) {
            byte first = encoded.charAt(i)
            byte second = encoded.charAt(i + 1)
            byte third = encoded.charAt(i + 2)
            byte fourth = encoded.charAt(i + 3)

            bytes.add((FROM_BASE64[first] << 2) + (FROM_BASE64[second] >>> 4));

            byte value = FROM_BASE64[second] << 4
            if (third != PADDING_SYMBOL) {
                bytes.add(value += FROM_BASE64[third] >>> 2)
            }

            value = FROM_BASE64[third] << 6
            if (fourth != PADDING_SYMBOL) {
                bytes.add(value + FROM_BASE64[fourth])
            }
        }
        return bytes
    }

}
