#ifndef __JY_H__
#define __JY_H__

namespace jy{
  template<typename T>
  struct is_pointer { static const bool value = false; };

  template<typename T>
  struct is_pointer<T*> { static const bool value = true; };
}

#endif
