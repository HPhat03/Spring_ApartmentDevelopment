const validAnswer = [
    "Bạn có thể liên hệ thuê phòng thông qua trang facebook chính thức của PNA hoặc là thông qua zalo theo số 03xxxxx, hoặc liên hệ trực tiếp với phòng dịch vụ của PNA",
    "Tài khoản của bạn sẽ được cung cấp thông qua tin nhắn SMS khi bạn đăng kí đứng tên thuê ít nhất là 1 căn hộ chung cư của PNA",
    "Bạn có thể xem hóa đơn của mình thông qua mục Hóa đơn trong các căn hộ của mình",
    "Quý khách có thể cho người thân vào thăm, nhằm phục vụ cho an ninh và an toàn cho tất cả mọi người, mọi cá nhân trong tào nhà cần được nhận biết rõ ràng. Quý khách có thể đăng kí thông qua mục đăng kí người thân",
    "Với bất kì thiệt hại hoặc hư hỏng nào, Quý khách có thể báo cáo với chúng tôi để kịp thời sữa chữa, thông qua mục báo cáo trong mỗi mục chung cư của quí khách",
    "Với các món hàng khi được giao hàng đến, chúng tôi sẽ nhận và giữ hộ quý khách, nên quý khách hãy thường xuyên kiểm tra mục Tủ thông minh của mình",
    "Đây là website giúp quản lí chung cư của bạn một cách tiện lợi, bạn có thể xem hóa đơn, báo cáo rủi ro, nơi trao đổi với chủ nhà."
]
const key = [
    ["liên", "hệ", "thuê", "phòng", "muốn", "ai", "lạc"],
    ["tài", "khoản"],
    ["hóa","đơn"],
    ["người", "thân", "nhà", "thăm", "kí"],
    ["thiệt", "hại","bị", "hư", "sai", "sót", "có", "hỏng", "báo", "cáo"],
    ["hàng", "hóa", "ship"],
    ["web","này", "website", "gì",]
]

export const getAnswer = (str) => {
    const token = str.trim().split(" ")
    const classification = [0,0,0,0,0,0,0]

    for( var i = 0; i< key.length; i++)
        for( var j = 0; j<token.length; j++)
        {   
            console.log(key[i].indexOf(token[j]))
            if(key[i].indexOf(token[j]) > -1)
                classification[i] += 1
        }
    console.log(classification)
    const max = Math.max(...classification)
    return max <= 1 ? "" : validAnswer[classification.indexOf(max)]
}