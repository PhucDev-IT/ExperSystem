package vn.mobile.expersystem.database.core

import vn.mobile.expersystem.models.ImageModel
import vn.mobile.expersystem.models.Nhom
import vn.mobile.expersystem.models.TapLuat
import vn.mobile.expersystem.models.TapSuKien

object DataSample {
    var groups = mutableListOf<Nhom>(
        Nhom("U","UserNeed","Nhu cầu người dùng"),
        Nhom("P","Price","Giá cả "),
        Nhom("E","Engine","Động cơ"),
        Nhom("T","Type","Loại xe"),
        Nhom("N","Trademark","Nhãn hiệu"),
        Nhom("D","Design","Thiết kế"),
    )

    var groupEvents = mutableListOf<TapSuKien>(
        TapSuKien("U1","U","Xe giá rẻ"),
        TapSuKien("U2","U","Tiết kiệm nhiên liệu"),
        TapSuKien("U3","U","Đi làm"),
        TapSuKien("U4","U","Vận tải"),
        TapSuKien("U5","U","Công suất mạnh"),
        TapSuKien("P1","P","Dưới 300 triệu đồng"),
        TapSuKien("P2","P","Dưới 1 tỉ"),
        TapSuKien("P3","P","Trên 1 tỉ"),
        TapSuKien("P4","P","Dưới 700 triệu"),
        TapSuKien("E1","E","Động cơ đốt trong (sử dụng nhiên liệu xăng hoặc dầu Diesel)"),
        TapSuKien("E2","E","Động cơ điện"),
        TapSuKien("E3","E","Động cơ lai hybrid."),
        TapSuKien("T1","T","Xe con"),
        TapSuKien("T2","T","Xe khách"),
        TapSuKien("T3","T","Xe 2 -4 chỗ ngồi"),
        TapSuKien("N1","N","Nhãn hiệu Audi"),
        TapSuKien("N2","N","Nhãn hiệu Toyota"),
        TapSuKien("N3","N","Nhãn hiệu BMW"),
        TapSuKien("N4","N","Nhãn hiệu Suzuki"),
        TapSuKien("N5","N","Nhãn hiệu Ferrari"),
        TapSuKien("N6","N","Nhãn hiệu Honda"),
        TapSuKien("N7","N","Nhãn hiệu Mercedes-Benz"),
        TapSuKien("N8","N","Nhãn hiệu Lamborghini"),
        TapSuKien("N9","N","Nhãn hiệu VinFast"),
        TapSuKien("N10","N","Nhãn hiệu Porsche"),
        TapSuKien("D1","D","Thiết kế sang trọng"),
        TapSuKien("D2","D","Thiết kế hiện đại"),
        TapSuKien("D3","D","Thiết kế thể thao"),
        TapSuKien("D4","D","Thiết kế cổ điển"),
    )

    var groupRules = mutableListOf<TapLuat>(
        TapLuat(maLuat = "L1",suKienId = "U2 ^ U1",ketLuan = "- Hyundai Grand i10: Từ 360 triệu đồng, 4.6L/100km.\n" +
                "- Toyota Wigo: Từ 360 triệu đồng, 5.3L/100km.\n" +
                "- KIA Morning: Từ 369 triệu đồng, 5.8L/100km.\n" +
                "- Mitsubishi Attrage: Từ 380 triệu đồng, 5L/100km."),

        TapLuat(maLuat = "L3", suKienId = "N3 ^ N1", ketLuan = "- Honda City: Từ 529 triệu đồng, phù hợp với đi làm hằng ngày.\n" +
                "- Toyota Vios: Từ 479 triệu đồng, tiết kiệm nhiên liệu, phù hợp cho việc đi lại hằng ngày."),

        TapLuat(maLuat = "L4", suKienId = "N4 ^ N1", ketLuan = "- Ford Transit: Từ 845 triệu đồng, có thể chở nhiều hành lý và người, phù hợp vận tải."),

        TapLuat(maLuat = "L5", suKienId = "N5 ^ N1", ketLuan = "- Mazda CX-5: Từ 839 triệu đồng, công suất mạnh mẽ, phù hợp di chuyển đường dài và địa hình khó."),

        TapLuat(maLuat = "L6", suKienId = "N3 ^ N2", ketLuan = "- Toyota Corolla Altis: Từ 733 triệu đồng, thiết kế hiện đại, thích hợp để đi làm."),

        TapLuat(maLuat = "L7", suKienId = "N4 ^ N2", ketLuan = "- Toyota Hiace: Từ 1 tỷ đồng, chở được nhiều người, phục vụ vận tải hành khách."),

        TapLuat(maLuat = "L8", suKienId = "N5 ^ N2", ketLuan = "- Ford Ranger: Từ 650 triệu đồng, phù hợp cho các công việc yêu cầu công suất cao và bền bỉ."),

        TapLuat(maLuat = "L12", suKienId = "N3 ^ N2 ^ N1", ketLuan = "- Toyota Camry: Giá từ 1.1 tỷ đồng, tiết kiệm nhiên liệu và phù hợp di chuyển công việc.\n" +
                "- Kia Optima: Giá từ 850 triệu, thiết kế hiện đại, phù hợp đi làm và tiết kiệm."),

        TapLuat(maLuat = "L15", suKienId = "N4 ^ N3 ^ N1", ketLuan = "- Ford Ranger: Xe bán tải đa năng cho di chuyển công việc và vận tải nhẹ, giá từ 616 triệu.\n" +
                "- Nissan Navara: Từ 630 triệu, bền bỉ, phù hợp cho vận tải và di chuyển hằng ngày."),

        TapLuat(maLuat = "L16", suKienId = "N5 ^ N2 ^ N1", ketLuan = "- Audi Q7: Giá từ 4.2 tỷ, xe sang trọng, công suất cao và tiết kiệm nhiên liệu.\n" +
                "- Lexus RX: Giá từ 3.8 tỷ, động cơ mạnh mẽ, tiết kiệm nhiên liệu và sang trọng."),

        TapLuat(maLuat = "L20", suKienId = "N5 ^ N4 ^ N3 ^ N2 ^ N1", ketLuan = "- Lamborghini Urus: Xe thể thao cao cấp cho mọi nhu cầu, giá từ 23 tỷ đồng.\n" +
                "- Ferrari Portofino: Giá từ 17 tỷ, hiệu suất cao và thiết kế sang trọng, đáp ứng đa dạng mục đích."),
        TapLuat(maLuat = "L21", suKienId = "N4 ^ N3 ^ N1", ketLuan = "- Mitsubishi Triton: Công suất mạnh, phù hợp cho vận tải và đi làm."),
        TapLuat(maLuat = "L22", suKienId = "N5 ^ N2 ^ N1", ketLuan = "- Porsche Cayenne: Công suất mạnh, tiết kiệm nhiên liệu và giá hợp lý."),
        TapLuat(maLuat = "L23", suKienId = "N4 ^ N3 ^ N1", ketLuan = "- Mazda BT-50: Tối ưu cho vận tải, tiết kiệm nhiên liệu và giá hợp lý."),
        TapLuat(maLuat = "L24", suKienId = "N5 ^ N3 ^ N1", ketLuan = "- Range Rover: Công suất mạnh, phù hợp cho vận tải và đi làm."),
        TapLuat(maLuat = "L25", suKienId = "N5 ^ N4 ^ N1", ketLuan = "- Chevrolet Colorado: Công suất mạnh, phù hợp cho vận tải và đi làm."),
                TapLuat(maLuat = "L26", suKienId = "N5 ^ N4 ^ N3 ^ N2 ^ N1", ketLuan = "- Lamborghini Urus: Xe thể thao cao cấp cho mọi nhu cầu, giá từ 23 tỷ đồng.\n" +
                "- Ferrari Portofino: Giá từ 17 tỷ, hiệu suất cao và thiết kế sang trọng, đáp ứng đa dạng mục đích."),

        TapLuat(maLuat = "L27", suKienId = "N5 ^ N4 ^ N3 ^ N2", ketLuan = "- GMC Sierra: Công suất mạnh, phù hợp cho vận tải và tiết kiệm nhiên liệu."),
        TapLuat(maLuat = "L28", suKienId = "N5 ^ N4 ^ N3 ^ N1", ketLuan = "- Dodge Ram: Công suất mạnh, phù hợp cho vận tải và đi làm."),
        TapLuat(maLuat = "L29", suKienId = "N4 ^ N3 ^ N2 ^ N1", ketLuan = "- Honda CR-V: Tối ưu cho đi làm, vận tải và tiết kiệm nhiên liệu."),
        TapLuat(maLuat = "L30", suKienId = "N5 ^ N4 ^ N3", ketLuan = "- Lamborghini Urus: Công suất mạnh, thiết kế sang trọng và hiện đại."),

        TapLuat(maLuat = "L31", suKienId = "U1 ^ P1 ^ E1 ^ T1 ^ D1", ketLuan = "Hãng xe Audi không có mẫu xe nào nằm trong phạm vi tầm giá này"),
        TapLuat(maLuat = "L32", suKienId = "U2 ^ P2 ^ E2 ^ T2 ^ D2", ketLuan = "- Xe tiết kiệm nhiên liệu giá dưới 1 tỉ đồng, thích hợp cho gia đình với động cơ điện và thiết kế hiện đại."),
        TapLuat(maLuat = "L34", suKienId = "U3 ^ P3 ^ E3 ^ T3 ^ D3", ketLuan = " Xe mạnh mẽ, giá trên 1 tỉ đồng, thích hợp để đi xa với động cơ hybrid và thiết kế thể thao."),
        TapLuat(maLuat = "L35", suKienId = "U4 ^ P4 ^ E1 ^ T1 ^ D4", ketLuan = "- Xe vận tải, giá dưới 700 triệu đồng với động cơ đốt trong và thiết kế cổ điển, phù hợp để chở hàng"),
        TapLuat(maLuat = "L36", suKienId = "U5 ^ P3 ^ E2 ^ T2 ^ D2", ketLuan = " Xe công suất mạnh, giá trên 1 tỉ đồng, sử dụng động cơ điện và thiết kế hiện đại, thích hợp cho công việc"),
        TapLuat(maLuat = "L37", suKienId = "U1 ^ P2 ^ E1 ^ T1 ^ D1", ketLuan = "- Xe giá rẻ dưới 1 tỉ đồng với động cơ đốt trong và thiết kế sang trọng, thích hợp để đi lại hàng ngày"),
        TapLuat(maLuat = "L38", suKienId = "U3 ^ P1 ^ E3 ^ T3 ^ D3", ketLuan = "- Xe đi làm với giá dưới 300 triệu đồng, động cơ hybrid và thiết kế thể thao, phù hợp cho người trẻ."),
        TapLuat(maLuat = "L39", suKienId = "U5 ^ P4 ^ E2 ^ T2 ^ D1", ketLuan = " Xe bền bỉ, giá dưới 700 triệu đồng, với động cơ điện và thiết kế sang trọng, thích hợp để di chuyển dài hạn."),
        TapLuat(maLuat = "L40", suKienId = "U4 ^ P3 ^ E1 ^ T1 ^ D4", ketLuan = "- Xe vận tải công suất cao, giá trên 1 tỉ đồng, với động cơ đốt trong và thiết kế cổ điển, phù hợp để vận chuyển hàng nặng."),
        TapLuat(maLuat = "L41", suKienId = "U2 ^ P1 ^ E3 ^ T2 ^ D2", ketLuan = "- Xe tiết kiệm nhiên liệu với giá dưới 300 triệu đồng, động cơ hybrid và thiết kế hiện đại, lý tưởng cho gia đình."),
        TapLuat(maLuat = "L42", suKienId = "U1 ^ P2 ^ E2 ^ T1 ^ D3", ketLuan = "- Toyota Corolla Cross: Giá dưới 1 tỉ đồng, phù hợp để đi lại hàng ngày, tiết kiệm nhiên liệu với động cơ điện và thiết kế thể thao."),
        TapLuat(maLuat = "L43", suKienId = "U2 ^ P3 ^ E1 ^ T2 ^ D2", ketLuan = "- Honda CR-V: Giá trên 1 tỉ đồng, tiết kiệm nhiên liệu với động cơ đốt trong, thiết kế hiện đại và không gian rộng rãi cho gia đình."),
        TapLuat(maLuat = "L44", suKienId = "U3 ^ P1 ^ E3 ^ T3 ^ D1", ketLuan = "- Mazda MX-5: Giá dưới 300 triệu đồng, động cơ hybrid phù hợp cho người đi làm, phong cách sang trọng và thiết kế nhỏ gọn."),
        TapLuat(maLuat = "L45", suKienId = "U4 ^ P4 ^ E1 ^ T2 ^ D4", ketLuan = "- Ford Transit: Giá dưới 700 triệu đồng, thiết kế cổ điển, phù hợp cho vận chuyển và sử dụng động cơ đốt trong bền bỉ."),
        TapLuat(maLuat = "L46", suKienId = "U5 ^ P3 ^ E2 ^ T1 ^ D2", ketLuan = "- Tesla Model 3: Giá trên 1 tỉ đồng, công suất cao, động cơ điện tiết kiệm nhiên liệu và thiết kế hiện đại."),
        TapLuat(maLuat = "L47", suKienId = "U3 ^ P2 ^ E3 ^ T3 ^ D3", ketLuan = "- BMW i3: Giá dưới 1 tỉ đồng, dành cho người đi làm, động cơ hybrid tiết kiệm nhiên liệu và thiết kế thể thao nhỏ gọn."),
        TapLuat(maLuat = "L48", suKienId = "U1 ^ P4 ^ E1 ^ T1 ^ D1", ketLuan = "- Suzuki Celerio: Giá dưới 700 triệu đồng, xe nhỏ gọn giá rẻ, sử dụng động cơ đốt trong và thiết kế sang trọng."),
        TapLuat(maLuat = "L49", suKienId = "U2 ^ P1 ^ E2 ^ T2 ^ D2", ketLuan = "- Nissan Leaf: Giá dưới 300 triệu đồng, xe tiết kiệm nhiên liệu với động cơ điện, thiết kế hiện đại và không gian rộng rãi."),
        TapLuat(maLuat = "L50", suKienId = "U4 ^ P3 ^ E1 ^ T2 ^ D1", ketLuan = "- Mercedes-Benz Sprinter: Giá trên 1 tỉ đồng, xe vận tải với thiết kế sang trọng, động cơ đốt trong bền bỉ và không gian rộng rãi."),
        TapLuat(maLuat = "L51", suKienId = "U5 ^ P2 ^ E3 ^ T1 ^ D4", ketLuan = " Lexus RX: Giá dưới 1 tỉ đồng, xe công suất cao, động cơ hybrid và thiết kế cổ điển."),
        TapLuat(maLuat = "L52", suKienId = "D2 ^ E2 ^ N3", ketLuan = "- BMW iX3: Xe sử dụng động cơ điện, nhãn hiệu BMW, thiết kế hiện đại, phù hợp cho người yêu thích công nghệ và phong cách sang trọng."),
        TapLuat(maLuat = "L53", suKienId = "T1 ^ P3 ^ N7", ketLuan = "- Mercedes-Benz S-Class: Xe con cao cấp, giá trên 1 tỉ đồng, nhãn hiệu Mercedes-Benz, sang trọng và đầy đủ tiện nghi."),
        TapLuat(maLuat = "L54", suKienId = "U2 ^ E1 ^ T2", ketLuan = "- Toyota Hiace: Xe khách rộng rãi, tiết kiệm nhiên liệu với động cơ đốt trong, phù hợp cho kinh doanh vận tải."),
        TapLuat(maLuat = "L55", suKienId = "N4 ^ E3 ^ D3", ketLuan = "- Suzuki Swift Hybrid: Xe nhãn hiệu Suzuki với động cơ lai hybrid, thiết kế thể thao và tiết kiệm nhiên liệu"),
        TapLuat(maLuat = "L56", suKienId = "U3 ^ P1 ^ T3", ketLuan = "- Hyundai i10: Xe đi làm giá dưới 300 triệu đồng, phù hợp cho người làm văn phòng với kích thước nhỏ gọn và linh hoạt trong đô thị."),
        TapLuat(maLuat = "L57", suKienId = "P4 ^ E2 ^ D1", ketLuan = "- Nissan Leaf: Xe điện giá dưới 700 triệu đồng, thiết kế sang trọng và thân thiện với môi trường."),
        TapLuat(maLuat = "L58", suKienId = "E2 ^ N1 ^ D4", ketLuan = "- Audi e-tron: Xe điện, nhãn hiệu Audi với thiết kế cổ điển, phù hợp cho người yêu thích phong cách và công nghệ."),
        TapLuat(maLuat = "L59", suKienId = "T2 ^ P2 ^ D2", ketLuan = "- Ford Tourneo: Xe khách giá dưới 1 tỉ đồng, thiết kế hiện đại và nội thất tiện nghi, phù hợp cho gia đình lớn hoặc doanh nghiệp."),
        TapLuat(maLuat = "L60", suKienId = "U1 ^ D3 ^ N10", ketLuan = "- Porsche 911: Xe giá rẻ hơn trong phân khúc Porsche, thiết kế thể thao, phù hợp cho những ai đam mê tốc độ và phong cách."),
        TapLuat(maLuat = "L61", suKienId = "E1 ^ T1 ^ P4", ketLuan = "- Honda City: Xe con sử dụng động cơ đốt trong, giá dưới 700 triệu đồng, phù hợp cho gia đình nhỏ và di chuyển trong đô thị."),
        TapLuat(maLuat = "L62", suKienId = "U5 ^ E3 ^ N9", ketLuan = "- VinFast VF e34: Xe điện của VinFast, mạnh mẽ và thân thiện với môi trường, phù hợp cho người cần công suất cao."),
        TapLuat(maLuat = "L63", suKienId = "P2 ^ D1 ^ N8", ketLuan = "- Lamborghini Huracán: Xe sang trọng, giá dưới 1 tỉ, nhãn hiệu Lamborghini, nổi bật với phong cách mạnh mẽ và đẳng cấp."),
        TapLuat(maLuat = "L64", suKienId = "E1 ^ N4 ^ T3", ketLuan = "- Suzuki Celerio: Xe nhỏ gọn, động cơ đốt trong, giá vừa phải và phù hợp cho người mới sử dụng."),
        TapLuat(maLuat = "L65", suKienId = "T2 ^ D2 ^ N6", ketLuan = "- Honda Odyssey: Xe khách với thiết kế hiện đại, nội thất rộng rãi, phù hợp cho gia đình lớn hoặc công ty."),
        TapLuat(maLuat = "L66", suKienId = "P3 ^ E1 ^ N10", ketLuan = "- Porsche Panamera: Xe sang động cơ đốt trong, giá trên 1 tỉ, thiết kế lịch lãm, phù hợp cho doanh nhân."),
        TapLuat(maLuat = "L67", suKienId = "U4 ^ N7 ^ T1", ketLuan = "- Mercedes-Benz Sprinter: Xe vận tải nhỏ, nhãn hiệu Mercedes-Benz, thiết kế xe con nhưng không gian rộng rãi, bền bỉ."),
        TapLuat(maLuat = "L68", suKienId = "U3 ^ E2 ^ N2", ketLuan = "- Toyota Prius: Xe điện của Toyota, thiết kế nhỏ gọn, tiết kiệm nhiên liệu, lý tưởng cho di chuyển hàng ngày."),
        TapLuat(maLuat = "L69", suKienId = "D3 ^ E3 ^ T3", ketLuan = "- Hyundai Kona Hybrid: Xe lai hybrid với thiết kế thể thao, thích hợp cho những ai muốn sự tiện lợi và phong cách."),
        TapLuat(maLuat = "L70", suKienId = "N10 ^ P4 ^ E1", ketLuan = "- Porsche Cayenne: Xe đốt trong với thiết kế SUV, giá dưới 700 triệu đồng, mang lại trải nghiệm lái vượt trội."),
        TapLuat(maLuat = "L71", suKienId = "T1 ^ D4 ^ N1", ketLuan = " Audi A3: Xe con cổ điển của Audi, có phong cách thiết kế sang trọng và nhỏ gọn cho đô thị."),


    )


    val images = mutableListOf<ImageModel>(
        ImageModel(tapLuatId = 1, hinhAnhXe = "https://png.pngtree.com/png-vector/20240309/ourmid/pngtree-black-super-car-png-image_11921537.png"),
        ImageModel(tapLuatId = 1, hinhAnhXe = "https://sohanews.sohacdn.com/160588918557773824/2024/4/6/105d3222628t84309l0-1712408291347-17124082915341470103279.jpg"),
        ImageModel(tapLuatId = 1, hinhAnhXe = "https://sohanews.sohacdn.com/160588918557773824/2024/4/6/53792f68-20200428065620-1712411426488-1712411427901243828315.jpg"),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
//        ImageModel(tapLuatId = 1, hinhAnhXe = ""),
    )
}