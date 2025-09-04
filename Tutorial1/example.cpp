#include <iostream>

void passByValue(int x) {
    std::cout << "Inside passByValue (param address): " << &x << std::endl;
    x = 100; // Modifies the copy
}

void passByReference(int &x) {
    std::cout << "Inside passByReference (param address): " << &x << std::endl;
    x = 200; // Modifies the original variable
}

int main() {
    int myNum = 10;
    std::cout << "Original myNum address: " << &myNum << std::endl;
    std::cout << "Original myNum value: " << myNum << std::endl;

    std::cout << "\n--- Calling passByValue ---" << std::endl;
    passByValue(myNum);
    std::cout << "After passByValue, myNum value: " << myNum << std::endl; // Output: 10

    std::cout << "\n--- Calling passByReference ---" << std::endl;
    passByReference(myNum);
    std::cout << "After passByReference, myNum value: " << myNum << std::endl; // Output: 200

    return 0;
}
