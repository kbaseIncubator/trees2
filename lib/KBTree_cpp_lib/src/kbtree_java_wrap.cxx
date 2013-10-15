/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.8
 * 
 * This file is not intended to be easily readable and contains a number of 
 * coding conventions designed to improve portability and efficiency. Do not make
 * changes to this file unless you know what you are doing--modify the SWIG 
 * interface file instead. 
 * ----------------------------------------------------------------------------- */

#define SWIGJAVA


#ifdef __cplusplus
/* SwigValueWrapper is described in swig.swg */
template<typename T> class SwigValueWrapper {
  struct SwigMovePointer {
    T *ptr;
    SwigMovePointer(T *p) : ptr(p) { }
    ~SwigMovePointer() { delete ptr; }
    SwigMovePointer& operator=(SwigMovePointer& rhs) { T* oldptr = ptr; ptr = 0; delete oldptr; ptr = rhs.ptr; rhs.ptr = 0; return *this; }
  } pointer;
  SwigValueWrapper& operator=(const SwigValueWrapper<T>& rhs);
  SwigValueWrapper(const SwigValueWrapper<T>& rhs);
public:
  SwigValueWrapper() : pointer(0) { }
  SwigValueWrapper& operator=(const T& t) { SwigMovePointer tmp(new T(t)); pointer = tmp; return *this; }
  operator T&() const { return *pointer.ptr; }
  T *operator&() { return pointer.ptr; }
};

template <typename T> T SwigValueInit() {
  return T();
}
#endif

/* -----------------------------------------------------------------------------
 *  This section contains generic SWIG labels for method/variable
 *  declarations/attributes, and other compiler dependent labels.
 * ----------------------------------------------------------------------------- */

/* template workaround for compilers that cannot correctly implement the C++ standard */
#ifndef SWIGTEMPLATEDISAMBIGUATOR
# if defined(__SUNPRO_CC) && (__SUNPRO_CC <= 0x560)
#  define SWIGTEMPLATEDISAMBIGUATOR template
# elif defined(__HP_aCC)
/* Needed even with `aCC -AA' when `aCC -V' reports HP ANSI C++ B3910B A.03.55 */
/* If we find a maximum version that requires this, the test would be __HP_aCC <= 35500 for A.03.55 */
#  define SWIGTEMPLATEDISAMBIGUATOR template
# else
#  define SWIGTEMPLATEDISAMBIGUATOR
# endif
#endif

/* inline attribute */
#ifndef SWIGINLINE
# if defined(__cplusplus) || (defined(__GNUC__) && !defined(__STRICT_ANSI__))
#   define SWIGINLINE inline
# else
#   define SWIGINLINE
# endif
#endif

/* attribute recognised by some compilers to avoid 'unused' warnings */
#ifndef SWIGUNUSED
# if defined(__GNUC__)
#   if !(defined(__cplusplus)) || (__GNUC__ > 3 || (__GNUC__ == 3 && __GNUC_MINOR__ >= 4))
#     define SWIGUNUSED __attribute__ ((__unused__)) 
#   else
#     define SWIGUNUSED
#   endif
# elif defined(__ICC)
#   define SWIGUNUSED __attribute__ ((__unused__)) 
# else
#   define SWIGUNUSED 
# endif
#endif

#ifndef SWIG_MSC_UNSUPPRESS_4505
# if defined(_MSC_VER)
#   pragma warning(disable : 4505) /* unreferenced local function has been removed */
# endif 
#endif

#ifndef SWIGUNUSEDPARM
# ifdef __cplusplus
#   define SWIGUNUSEDPARM(p)
# else
#   define SWIGUNUSEDPARM(p) p SWIGUNUSED 
# endif
#endif

/* internal SWIG method */
#ifndef SWIGINTERN
# define SWIGINTERN static SWIGUNUSED
#endif

/* internal inline SWIG method */
#ifndef SWIGINTERNINLINE
# define SWIGINTERNINLINE SWIGINTERN SWIGINLINE
#endif

/* exporting methods */
#if (__GNUC__ >= 4) || (__GNUC__ == 3 && __GNUC_MINOR__ >= 4)
#  ifndef GCC_HASCLASSVISIBILITY
#    define GCC_HASCLASSVISIBILITY
#  endif
#endif

#ifndef SWIGEXPORT
# if defined(_WIN32) || defined(__WIN32__) || defined(__CYGWIN__)
#   if defined(STATIC_LINKED)
#     define SWIGEXPORT
#   else
#     define SWIGEXPORT __declspec(dllexport)
#   endif
# else
#   if defined(__GNUC__) && defined(GCC_HASCLASSVISIBILITY)
#     define SWIGEXPORT __attribute__ ((visibility("default")))
#   else
#     define SWIGEXPORT
#   endif
# endif
#endif

/* calling conventions for Windows */
#ifndef SWIGSTDCALL
# if defined(_WIN32) || defined(__WIN32__) || defined(__CYGWIN__)
#   define SWIGSTDCALL __stdcall
# else
#   define SWIGSTDCALL
# endif 
#endif

/* Deal with Microsoft's attempt at deprecating C standard runtime functions */
#if !defined(SWIG_NO_CRT_SECURE_NO_DEPRECATE) && defined(_MSC_VER) && !defined(_CRT_SECURE_NO_DEPRECATE)
# define _CRT_SECURE_NO_DEPRECATE
#endif

/* Deal with Microsoft's attempt at deprecating methods in the standard C++ library */
#if !defined(SWIG_NO_SCL_SECURE_NO_DEPRECATE) && defined(_MSC_VER) && !defined(_SCL_SECURE_NO_DEPRECATE)
# define _SCL_SECURE_NO_DEPRECATE
#endif



/* Fix for jlong on some versions of gcc on Windows */
#if defined(__GNUC__) && !defined(__INTEL_COMPILER)
  typedef long long __int64;
#endif

/* Fix for jlong on 64-bit x86 Solaris */
#if defined(__x86_64)
# ifdef _LP64
#   undef _LP64
# endif
#endif

#include <jni.h>
#include <stdlib.h>
#include <string.h>


/* Support for throwing Java exceptions */
typedef enum {
  SWIG_JavaOutOfMemoryError = 1, 
  SWIG_JavaIOException, 
  SWIG_JavaRuntimeException, 
  SWIG_JavaIndexOutOfBoundsException,
  SWIG_JavaArithmeticException,
  SWIG_JavaIllegalArgumentException,
  SWIG_JavaNullPointerException,
  SWIG_JavaDirectorPureVirtual,
  SWIG_JavaUnknownError
} SWIG_JavaExceptionCodes;

typedef struct {
  SWIG_JavaExceptionCodes code;
  const char *java_exception;
} SWIG_JavaExceptions_t;


static void SWIGUNUSED SWIG_JavaThrowException(JNIEnv *jenv, SWIG_JavaExceptionCodes code, const char *msg) {
  jclass excep;
  static const SWIG_JavaExceptions_t java_exceptions[] = {
    { SWIG_JavaOutOfMemoryError, "java/lang/OutOfMemoryError" },
    { SWIG_JavaIOException, "java/io/IOException" },
    { SWIG_JavaRuntimeException, "java/lang/RuntimeException" },
    { SWIG_JavaIndexOutOfBoundsException, "java/lang/IndexOutOfBoundsException" },
    { SWIG_JavaArithmeticException, "java/lang/ArithmeticException" },
    { SWIG_JavaIllegalArgumentException, "java/lang/IllegalArgumentException" },
    { SWIG_JavaNullPointerException, "java/lang/NullPointerException" },
    { SWIG_JavaDirectorPureVirtual, "java/lang/RuntimeException" },
    { SWIG_JavaUnknownError,  "java/lang/UnknownError" },
    { (SWIG_JavaExceptionCodes)0,  "java/lang/UnknownError" }
  };
  const SWIG_JavaExceptions_t *except_ptr = java_exceptions;

  while (except_ptr->code != code && except_ptr->code)
    except_ptr++;

  jenv->ExceptionClear();
  excep = jenv->FindClass(except_ptr->java_exception);
  if (excep)
    jenv->ThrowNew(excep, msg);
}


/* Contract support */

#define SWIG_contract_assert(nullreturn, expr, msg) if (!(expr)) {SWIG_JavaThrowException(jenv, SWIG_JavaIllegalArgumentException, msg); return nullreturn; } else


#include <string>


#include <stdexcept>


#include <map>
#include <algorithm>
#include <stdexcept>


#include "kbtree/kbtree.hh"


#ifdef __cplusplus
extern "C" {
#endif

SWIGEXPORT jlong JNICALL Java_KBTreeUtil_KBTreeUtilJNI_new_1KBTree_1_1SWIG_10(JNIEnv *jenv, jclass jcls, jstring jarg1) {
  jlong jresult = 0 ;
  std::string *arg1 = 0 ;
  KBTreeLib::KBTree *result = 0 ;
  
  (void)jenv;
  (void)jcls;
  if(!jarg1) {
    SWIG_JavaThrowException(jenv, SWIG_JavaNullPointerException, "null string");
    return 0;
  }
  const char *arg1_pstr = (const char *)jenv->GetStringUTFChars(jarg1, 0); 
  if (!arg1_pstr) return 0;
  std::string arg1_str(arg1_pstr);
  arg1 = &arg1_str;
  jenv->ReleaseStringUTFChars(jarg1, arg1_pstr); 
  result = (KBTreeLib::KBTree *)new KBTreeLib::KBTree((std::string const &)*arg1);
  *(KBTreeLib::KBTree **)&jresult = result; 
  return jresult;
}


SWIGEXPORT jlong JNICALL Java_KBTreeUtil_KBTreeUtilJNI_new_1KBTree_1_1SWIG_11(JNIEnv *jenv, jclass jcls, jstring jarg1, jboolean jarg2) {
  jlong jresult = 0 ;
  std::string *arg1 = 0 ;
  bool arg2 ;
  KBTreeLib::KBTree *result = 0 ;
  
  (void)jenv;
  (void)jcls;
  if(!jarg1) {
    SWIG_JavaThrowException(jenv, SWIG_JavaNullPointerException, "null string");
    return 0;
  }
  const char *arg1_pstr = (const char *)jenv->GetStringUTFChars(jarg1, 0); 
  if (!arg1_pstr) return 0;
  std::string arg1_str(arg1_pstr);
  arg1 = &arg1_str;
  jenv->ReleaseStringUTFChars(jarg1, arg1_pstr); 
  arg2 = jarg2 ? true : false; 
  result = (KBTreeLib::KBTree *)new KBTreeLib::KBTree((std::string const &)*arg1,arg2);
  *(KBTreeLib::KBTree **)&jresult = result; 
  return jresult;
}


SWIGEXPORT jlong JNICALL Java_KBTreeUtil_KBTreeUtilJNI_new_1KBTree_1_1SWIG_12(JNIEnv *jenv, jclass jcls, jstring jarg1, jboolean jarg2, jboolean jarg3) {
  jlong jresult = 0 ;
  std::string *arg1 = 0 ;
  bool arg2 ;
  bool arg3 ;
  KBTreeLib::KBTree *result = 0 ;
  
  (void)jenv;
  (void)jcls;
  if(!jarg1) {
    SWIG_JavaThrowException(jenv, SWIG_JavaNullPointerException, "null string");
    return 0;
  }
  const char *arg1_pstr = (const char *)jenv->GetStringUTFChars(jarg1, 0); 
  if (!arg1_pstr) return 0;
  std::string arg1_str(arg1_pstr);
  arg1 = &arg1_str;
  jenv->ReleaseStringUTFChars(jarg1, arg1_pstr); 
  arg2 = jarg2 ? true : false; 
  arg3 = jarg3 ? true : false; 
  result = (KBTreeLib::KBTree *)new KBTreeLib::KBTree((std::string const &)*arg1,arg2,arg3);
  *(KBTreeLib::KBTree **)&jresult = result; 
  return jresult;
}


SWIGEXPORT void JNICALL Java_KBTreeUtil_KBTreeUtilJNI_delete_1KBTree(JNIEnv *jenv, jclass jcls, jlong jarg1) {
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  delete arg1;
}


SWIGEXPORT jstring JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1toNewick_1_1SWIG_10(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_) {
  jstring jresult = 0 ;
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  std::string result;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  result = (arg1)->toNewick();
  jresult = jenv->NewStringUTF((&result)->c_str()); 
  return jresult;
}


SWIGEXPORT void JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1setOutputFlagLabel(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_, jboolean jarg2) {
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  bool arg2 ;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  arg2 = jarg2 ? true : false; 
  (arg1)->setOutputFlagLabel(arg2);
}


SWIGEXPORT void JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1setOutputFlagDistances(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_, jboolean jarg2) {
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  bool arg2 ;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  arg2 = jarg2 ? true : false; 
  (arg1)->setOutputFlagDistances(arg2);
}


SWIGEXPORT void JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1setOutputFlagComments(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_, jboolean jarg2) {
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  bool arg2 ;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  arg2 = jarg2 ? true : false; 
  (arg1)->setOutputFlagComments(arg2);
}


SWIGEXPORT void JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1setOutputFlagBootstrapValuesAsLabels(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_, jboolean jarg2) {
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  bool arg2 ;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  arg2 = jarg2 ? true : false; 
  (arg1)->setOutputFlagBootstrapValuesAsLabels(arg2);
}


SWIGEXPORT jstring JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1toNewick_1_1SWIG_11(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_, jlong jarg2) {
  jstring jresult = 0 ;
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  unsigned int arg2 ;
  std::string result;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  arg2 = (unsigned int)jarg2; 
  result = (arg1)->toNewick(arg2);
  jresult = jenv->NewStringUTF((&result)->c_str()); 
  return jresult;
}


SWIGEXPORT jboolean JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1writeNewickToFile_1_1SWIG_10(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_, jstring jarg2) {
  jboolean jresult = 0 ;
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  std::string *arg2 = 0 ;
  bool result;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  if(!jarg2) {
    SWIG_JavaThrowException(jenv, SWIG_JavaNullPointerException, "null string");
    return 0;
  }
  const char *arg2_pstr = (const char *)jenv->GetStringUTFChars(jarg2, 0); 
  if (!arg2_pstr) return 0;
  std::string arg2_str(arg2_pstr);
  arg2 = &arg2_str;
  jenv->ReleaseStringUTFChars(jarg2, arg2_pstr); 
  result = (bool)(arg1)->writeNewickToFile((std::string const &)*arg2);
  jresult = (jboolean)result; 
  return jresult;
}


SWIGEXPORT jboolean JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1writeNewickToFile_1_1SWIG_11(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_, jstring jarg2, jlong jarg3) {
  jboolean jresult = 0 ;
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  std::string *arg2 = 0 ;
  unsigned int arg3 ;
  bool result;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  if(!jarg2) {
    SWIG_JavaThrowException(jenv, SWIG_JavaNullPointerException, "null string");
    return 0;
  }
  const char *arg2_pstr = (const char *)jenv->GetStringUTFChars(jarg2, 0); 
  if (!arg2_pstr) return 0;
  std::string arg2_str(arg2_pstr);
  arg2 = &arg2_str;
  jenv->ReleaseStringUTFChars(jarg2, arg2_pstr); 
  arg3 = (unsigned int)jarg3; 
  result = (bool)(arg1)->writeNewickToFile((std::string const &)*arg2,arg3);
  jresult = (jboolean)result; 
  return jresult;
}


SWIGEXPORT void JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1removeNodesByNameAndSimplify(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_, jstring jarg2) {
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  std::string *arg2 = 0 ;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  if(!jarg2) {
    SWIG_JavaThrowException(jenv, SWIG_JavaNullPointerException, "null string");
    return ;
  }
  const char *arg2_pstr = (const char *)jenv->GetStringUTFChars(jarg2, 0); 
  if (!arg2_pstr) return ;
  std::string arg2_str(arg2_pstr);
  arg2 = &arg2_str;
  jenv->ReleaseStringUTFChars(jarg2, arg2_pstr); 
  (arg1)->removeNodesByNameAndSimplify((std::string const &)*arg2);
}


SWIGEXPORT void JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1mergeZeroDistLeaves(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_) {
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  (arg1)->mergeZeroDistLeaves();
}


SWIGEXPORT void JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1replaceNodeNames(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_, jstring jarg2) {
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  std::string *arg2 = 0 ;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  if(!jarg2) {
    SWIG_JavaThrowException(jenv, SWIG_JavaNullPointerException, "null string");
    return ;
  }
  const char *arg2_pstr = (const char *)jenv->GetStringUTFChars(jarg2, 0); 
  if (!arg2_pstr) return ;
  std::string arg2_str(arg2_pstr);
  arg2 = &arg2_str;
  jenv->ReleaseStringUTFChars(jarg2, arg2_pstr); 
  (arg1)->replaceNodeNames((std::string const &)*arg2);
}


SWIGEXPORT void JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1replaceNodeNamesOrMakeBlank(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_, jstring jarg2) {
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  std::string *arg2 = 0 ;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  if(!jarg2) {
    SWIG_JavaThrowException(jenv, SWIG_JavaNullPointerException, "null string");
    return ;
  }
  const char *arg2_pstr = (const char *)jenv->GetStringUTFChars(jarg2, 0); 
  if (!arg2_pstr) return ;
  std::string arg2_str(arg2_pstr);
  arg2 = &arg2_str;
  jenv->ReleaseStringUTFChars(jarg2, arg2_pstr); 
  (arg1)->replaceNodeNamesOrMakeBlank((std::string const &)*arg2);
}


SWIGEXPORT void JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1stripReservedCharsFromLabels(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_) {
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  (arg1)->stripReservedCharsFromLabels();
}


SWIGEXPORT void JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1printTree(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_) {
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  (arg1)->printTree();
}


SWIGEXPORT jstring JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1printSimpleTreeToString(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_) {
  jstring jresult = 0 ;
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  std::string result;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  result = (arg1)->printSimpleTreeToString();
  jresult = jenv->NewStringUTF((&result)->c_str()); 
  return jresult;
}


SWIGEXPORT jstring JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1printTreeToString(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_) {
  jstring jresult = 0 ;
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  std::string result;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  result = (arg1)->printTreeToString();
  jresult = jenv->NewStringUTF((&result)->c_str()); 
  return jresult;
}


SWIGEXPORT jlong JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1getNodeCount(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_) {
  jlong jresult = 0 ;
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  unsigned int result;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  result = (unsigned int)((KBTreeLib::KBTree const *)arg1)->getNodeCount();
  jresult = (jlong)result; 
  return jresult;
}


SWIGEXPORT jlong JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1getLeafCount(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_) {
  jlong jresult = 0 ;
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  unsigned int result;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  result = (unsigned int)(arg1)->getLeafCount();
  jresult = (jlong)result; 
  return jresult;
}


SWIGEXPORT jstring JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1getAllLeafNames(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_) {
  jstring jresult = 0 ;
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  std::string result;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  result = (arg1)->getAllLeafNames();
  jresult = jenv->NewStringUTF((&result)->c_str()); 
  return jresult;
}


SWIGEXPORT jstring JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1getAllNodeNames(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_) {
  jstring jresult = 0 ;
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  std::string result;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  result = (arg1)->getAllNodeNames();
  jresult = jenv->NewStringUTF((&result)->c_str()); 
  return jresult;
}


SWIGEXPORT void JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1resetBreadthFirstIterToRoot(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_) {
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  (arg1)->resetBreadthFirstIterToRoot();
}


SWIGEXPORT jboolean JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1breadthFirstIterNext(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_) {
  jboolean jresult = 0 ;
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  bool result;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  result = (bool)(arg1)->breadthFirstIterNext();
  jresult = (jboolean)result; 
  return jresult;
}


SWIGEXPORT jstring JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1breadthFirstIterGetName_1_1SWIG_10(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_) {
  jstring jresult = 0 ;
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  std::string result;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  result = (arg1)->breadthFirstIterGetName();
  jresult = jenv->NewStringUTF((&result)->c_str()); 
  return jresult;
}


SWIGEXPORT jstring JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1breadthFirstIterGetParentName_1_1SWIG_10(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_) {
  jstring jresult = 0 ;
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  std::string result;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  result = (arg1)->breadthFirstIterGetParentName();
  jresult = jenv->NewStringUTF((&result)->c_str()); 
  return jresult;
}


SWIGEXPORT jlong JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1breadthFirstIterMarkNode(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_) {
  jlong jresult = 0 ;
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  unsigned int result;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  result = (unsigned int)(arg1)->breadthFirstIterMarkNode();
  jresult = (jlong)result; 
  return jresult;
}


SWIGEXPORT jboolean JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1breadthFirstIterSetToNode(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_, jlong jarg2) {
  jboolean jresult = 0 ;
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  unsigned int arg2 ;
  bool result;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  arg2 = (unsigned int)jarg2; 
  result = (bool)(arg1)->breadthFirstIterSetToNode(arg2);
  jresult = (jboolean)result; 
  return jresult;
}


SWIGEXPORT jstring JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1breadthFirstIterGetName_1_1SWIG_11(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_, jlong jarg2) {
  jstring jresult = 0 ;
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  unsigned int arg2 ;
  std::string result;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  arg2 = (unsigned int)jarg2; 
  result = (arg1)->breadthFirstIterGetName(arg2);
  jresult = jenv->NewStringUTF((&result)->c_str()); 
  return jresult;
}


SWIGEXPORT jstring JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1breadthFirstIterGetPathToRoot(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_, jlong jarg2) {
  jstring jresult = 0 ;
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  unsigned int arg2 ;
  std::string result;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  arg2 = (unsigned int)jarg2; 
  result = (arg1)->breadthFirstIterGetPathToRoot(arg2);
  jresult = jenv->NewStringUTF((&result)->c_str()); 
  return jresult;
}


SWIGEXPORT jstring JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1breadthFirstIterGetParentName_1_1SWIG_11(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_, jlong jarg2) {
  jstring jresult = 0 ;
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  unsigned int arg2 ;
  std::string result;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  arg2 = (unsigned int)jarg2; 
  result = (arg1)->breadthFirstIterGetParentName(arg2);
  jresult = jenv->NewStringUTF((&result)->c_str()); 
  return jresult;
}


SWIGEXPORT jstring JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1breadthFirstIterGetAllChildrenNames(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_, jlong jarg2) {
  jstring jresult = 0 ;
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  unsigned int arg2 ;
  std::string result;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  arg2 = (unsigned int)jarg2; 
  result = (arg1)->breadthFirstIterGetAllChildrenNames(arg2);
  jresult = jenv->NewStringUTF((&result)->c_str()); 
  return jresult;
}


SWIGEXPORT jstring JNICALL Java_KBTreeUtil_KBTreeUtilJNI_KBTree_1breadthFirstIterGetAllDescendantNames(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg1_, jlong jarg2) {
  jstring jresult = 0 ;
  KBTreeLib::KBTree *arg1 = (KBTreeLib::KBTree *) 0 ;
  unsigned int arg2 ;
  std::string result;
  
  (void)jenv;
  (void)jcls;
  (void)jarg1_;
  arg1 = *(KBTreeLib::KBTree **)&jarg1; 
  arg2 = (unsigned int)jarg2; 
  result = (arg1)->breadthFirstIterGetAllDescendantNames(arg2);
  jresult = jenv->NewStringUTF((&result)->c_str()); 
  return jresult;
}


#ifdef __cplusplus
}
#endif

