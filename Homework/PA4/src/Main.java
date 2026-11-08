import pa4.Treap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    Treap<Integer> testTreap = new Treap<Integer>();
    testTreap.add(4 ,19);
    testTreap.add (2 ,31);
    testTreap.add (6 ,70);
    System.out.println(testTreap.toString());
}
