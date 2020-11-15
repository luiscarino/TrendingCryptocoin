//
//  TrendingCryptoRow.swift
//  iosApp
//
//  Created by Luis Carino on 11/14/20.
//  Copyright © 2020 orgName. All rights reserved.
//

import SwiftUI
import shared

struct TrendingCryptoRow: View {
    
    var cryptoCoin: CryptoCoinUI
    
    var body: some View {
            HStack() {
                VStack(alignment: .leading, spacing: 10.0) {
                    Text("Launch name: \(cryptoCoin.name)")
                    }
                Spacer()
            }
        }
}


struct TrendingCryptoRow_Previews: PreviewProvider {
    static var previews: some View {
        /*@START_MENU_TOKEN@*/Text("Hello, World!")/*@END_MENU_TOKEN@*/
    }
}
