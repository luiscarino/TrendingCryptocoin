import SwiftUI
import shared

struct CryptoCoinUI: Identifiable {
    var id: String
    var name: String
}

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel
    var body: some View {
            NavigationView {
                listView()
                .navigationBarTitle("Trending Cryptos")
                .navigationBarItems(trailing:
                    Button("Reload") {
                        self.viewModel.loadTrendingCryptos(forceReload: true)
                })
            }
        }
    
    private func listView() -> AnyView {
            switch viewModel.trendingCryptos {
            case .loading:
                return AnyView(Text("Loading...").multilineTextAlignment(.center))
            case .result(let coins):
                return AnyView(List(coins.map({CryptoCoinUI(id: $0.id, name: $0.name)})) { coin in
                    TrendingCryptoRow(cryptoCoin:coin)
                })
            case .error(let description):
                return AnyView(Text(description).multilineTextAlignment(.center))
            }
        }
}


extension ContentView {
    enum LoadableCryptos {
        case loading
        case result([Cryptocoin])
        case error(String)
    }

    class ViewModel: ObservableObject {
        let sdk: TrendingCryptoSDK
        @Published var trendingCryptos = LoadableCryptos.loading
    
    init(sdk: TrendingCryptoSDK) {
        self.sdk = sdk
        self.loadTrendingCryptos(forceReload: false)
    }
    
    func loadTrendingCryptos(forceReload: Bool) {
        self.trendingCryptos = .loading
        sdk.getTrendingCryptoCoins(completionHandler: {result,error in
            if let result = result {
                self.trendingCryptos = .result(result)
            } else {
                self.trendingCryptos = .error(error?.localizedDescription ?? "error")
            }
        })
    }
}
    
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        /*@START_MENU_TOKEN@*/Text("Hello, World!")/*@END_MENU_TOKEN@*/
    }
}
