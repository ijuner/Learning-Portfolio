public sealed interface Order permits OnlineOrder, InStoreOrder {

}
public final class OnlineOrder implements Order {}
public final class InStoreOrder implements Order {
 
}