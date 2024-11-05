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
        TapSuKien("P1","P","Dưới 100 triệu đồng"),
        TapSuKien("P2","P","Dưới 1 tỉ"),
        TapSuKien("P3","P","Trên 1 tỉ"),
        TapSuKien("P4","P","Dưới 700 triệu"),
        TapSuKien("E1","E","Động cơ đốt trong (sử dụng nhiên liệu xăng hoặc dầu Diesel)"),
        TapSuKien("E2","E","Động cơ điện"),
        TapSuKien("E3","E","động cơ lai hybrid."),
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
        TapLuat(maLuat = "L100",suKienId = "U1 ^ U2",ketLuan = "- Hyundai Grand i10: Từ 360 triệu đồng, 4.6L/100km.\n" +
                "- Toyota Wigo: Từ 360 triệu đồng, 5.3L/100km.\n" +
                "- KIA Morning: Từ 369 triệu đồng, 5.8L/100km.\n" +
                "- Mitsubishi Attrage: Từ 380 triệu đồng, 5L/100km."),

        TapLuat(maLuat = "L3", suKienId = "N3 ^ N1", ketLuan = "- Honda City: Từ 529 triệu đồng, phù hợp với đi làm hằng ngày.\n" +
                "- Toyota Vios: Từ 479 triệu đồng, tiết kiệm nhiên liệu, phù hợp cho việc đi lại hằng ngày."),

        TapLuat(maLuat = "L300", suKienId = "N1 ^ N3", ketLuan = "- Honda City: Từ 529 triệu đồng, phù hợp với đi làm hằng ngày.\n" +
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