/**
 * Created by izeye on 2014. 11. 20..
 */

// NOTE:
// The following test data come from http://en.wikipedia.org/wiki/Base64.
input1 = "any carnal pleasure."
input2 = "any carnal pleasure"
input3 = "any carnal pleasur"
input4 = "any carnal pleasu"
input5 = "any carnal pleas"
input6 = "pleasure."
input7 = "leasure."
input8 = "easure."
input9 = "asure."
input10 = "sure."

expectedOutput1 = "YW55IGNhcm5hbCBwbGVhc3VyZS4="
expectedOutput2 = "YW55IGNhcm5hbCBwbGVhc3VyZQ=="
expectedOutput3 = "YW55IGNhcm5hbCBwbGVhc3Vy"
expectedOutput4 = "YW55IGNhcm5hbCBwbGVhc3U="
expectedOutput5 = "YW55IGNhcm5hbCBwbGVhcw=="
expectedOutput6 = "cGxlYXN1cmUu"
expectedOutput7 = "bGVhc3VyZS4="
expectedOutput8 = "ZWFzdXJlLg=="
expectedOutput9 = "YXN1cmUu"
expectedOutput10 = "c3VyZS4="

testEncode(input1, expectedOutput1)
testEncode(input2, expectedOutput2)
testEncode(input3, expectedOutput3)
testEncode(input4, expectedOutput4)
testEncode(input5, expectedOutput5)
testEncode(input6, expectedOutput6)
testEncode(input7, expectedOutput7)
testEncode(input8, expectedOutput8)
testEncode(input9, expectedOutput9)
testEncode(input10, expectedOutput10)

testDecode(input1)
testDecode(input2)
testDecode(input3)
testDecode(input4)
testDecode(input5)
testDecode(input6)
testDecode(input7)
testDecode(input8)
testDecode(input9)
testDecode(input10)

private void testEncode(String input, String expectedOutput) {
    assert Base64InGroovy.encode(input.getBytes("UTF-8")) == expectedOutput
}

private void testDecode(String input) {
    assert Base64InGroovy.decode(Base64InGroovy.encode(input.getBytes("UTF-8"))) == input.getBytes("UTF-8")
}
