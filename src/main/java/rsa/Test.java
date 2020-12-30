package rsa;

public class Test {
    private static final String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAukjUCe0xtO3BQMKP/XOX2k82gvyTHrXpqMHUx3/ZeUJ8XbaihYjrtN4844Yb7/eJjuXDmtCo0tZjfIi0sAXCzkOPbf2ZfZuCNFiDAchE7d5RCvMeBmA9eytXDxbtk6as0BK3ktCvSVuQcwciavyhVGV4xkabEdsKKJc64kDETGGlk9JhIrUJY3Bq0u1CKVpXvBJATLaue8lv4gDAOa9LRXl83Fz0yD0qHZpvCT7+PILh2L6noiQd3OHlpfhnO4fqSAs0y6OaJjpKhk+3nWQHpzZK3PMe2lQjosWHl8Hvuva23FizKNfVMSdI4pkxCJVmv0aTnbVDWiQEcOBbkEfj0QIDAQAB";
    private static final String privateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC6SNQJ7TG07cFAwo/9c5faTzaC/JMetemowdTHf9l5QnxdtqKFiOu03jzjhhvv94mO5cOa0KjS1mN8iLSwBcLOQ49t/Zl9m4I0WIMByETt3lEK8x4GYD17K1cPFu2TpqzQEreS0K9JW5BzByJq/KFUZXjGRpsR2woolzriQMRMYaWT0mEitQljcGrS7UIpWle8EkBMtq57yW/iAMA5r0tFeXzcXPTIPSodmm8JPv48guHYvqeiJB3c4eWl+Gc7h+pICzTLo5omOkqGT7edZAenNkrc8x7aVCOixYeXwe+69rbcWLMo19UxJ0jimTEIlWa/RpOdtUNaJARw4FuQR+PRAgMBAAECggEAOB74woTTFM8MCZaUCqdoeDylD5FIQVhMrKq4j+HUgtOk5BbBn7lASZ0qeuFMSV8p8gMGiv2R3vL3xezpgp45sipoa5qbQ1w2Pc7sqPzMx1zSzZmw0frsis7jy4f8yx7LsWPyKij0pgUP35emptS1RdfMKu/ah+aViOUGuGMKwHem1gPl/g74etRgvEIFXukSeN8LUyCnM3H81gB1u7TlkCUrYuQAMQnRNXZP7XL/KJzZB/p+r9BAv1kjv1hgDV/6ZkHV0puRu+O2/6AyoI+ZpYc3NdWmHi4STISFLDRWpEFMfYw7Lkr5OIy/Egej/NbsDsYOJRe2EAmI8znOtA0KgQKBgQDkEGXrfSDNPxsl9iga5YdA+eI7bMAmy/YKiQz0uuL8wZ8vnG78rwgntHi2CpHWy0wBtB+/mqWmFQ1AKJw4uaIKSvT13FrhbFdahGwC7mm6NTLiimvCfundcXtwEna/Vq5wliYdfYkwXwCSbnPC8B8h6SEUffZ3QeTBAND93eZDwwKBgQDRGk/h6cR8va7yfoy0UpL3nXLD6cP8BCQXp9II86pEfeejCCHLA+q2E5AE3+9gV4qqjpWxIJwgcnAyqCVr1vRLyNthG3Q7Hm6EgI0q7FsNRm+91Q8Wz1QR++KMuT3f/rvRSMgL/WE3p1aJPokjWn+yTJN856gKOxrXmlhdhGGk2wKBgQDffc5S8gEWAVR6Y/ozOWDM5NO1RkIbAt7uvhVLMmVEKf30K0l8J45HUozEYCFAq2KlfL1sB4Fy+shMaj+FOyy3B3N8B9+rbn8//erXCInlZ0KhS+KD69kEH7RGRGHH6YryW8GoPqAVHdQyqziX/LWVhp7bNQE1JwajuEgVr8F5/wKBgQCjAG/lG+Yfl34Zaq0kpHoMrKAaONCeVFwOVLRWZhwE6GD5pg0xxQvig71xD1/7KkLeUeg++7y5TnxIAOuq9RXbPehF+rDQpRhiEhP5seYIoy/YO45XqadPnZstN/oT8u9WZfYL4qk2SSwcMLOW66U/yJHwfGVyFEEIcTkRLXEwoQKBgQCRJ0KtjA7+jCqf5SPMvCgHf3Ww9epA7Hy7Wqrhl4RaFHrm7PGwCuh/cza0E611GDc7pcvRZsfZ6mZjLf9GExTzWxFfhrssUZ/h8o2Nkx6alUel7vTMJNPY6p9eelxRxAmCxQSr72scsROmQL3Sy/emrXH5fPNcrhqorzz3lHFhTw==";

    public static void main(String[] args) {
        String content = "我是centent";
        System.out.println("明文是:" + content);

        try {
            String encodeContent = RSAUtils.encryptByPublicKey(content, publicKey);
            System.out.println("加密后是:" + encodeContent);

            String decryptContent = RSAUtils.decryptByPrivateKey(encodeContent, privateKey);
            System.out.println("解密后是:" + decryptContent);

            System.out.println("明文和解密后，是否相同？："+content.equals(decryptContent));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
