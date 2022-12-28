#include <Calculator.h>

// Implementing the methods.
JNIEXPORT jint JNICALL Java_Calculator_add(JNIEnv *env, jclass jclass, jint num1, jint num2) {
    return num1 + num2;
}

JNIEXPORT jint JNICALL Java_Calculator_subtract(JNIEnv *env, jclass jclass, jint num1, jint num2) {
    return num1 - num2;
}

JNIEXPORT jint JNICALL Java_Calculator_multiply(JNIEnv *env, jclass jclass, jint num1, jint num2) {
    return num1 * num2;
}

JNIEXPORT jint JNICALL Java_Calculator_divide(JNIEnv *env, jclass jclass, jint num1, jint num2) {
    return num1 / num2;
}