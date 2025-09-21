# 🏨 JavaSE Hotel Project

## 👥 Team Members
1. علیرضا نوراحمدی (Project Manager)
2. شایان امینائی
3. امیرسبحان قندی‌زاده
4. مهدیار

---

## 📆 Timeline
- پایان پروژه: ۱۵ مهر

---

## 📂 Project Structure

---

## 📐 UML Final
![Hotel UML](docs/hotel-uml.png)

---

## 🚀 Roadmap (Step by Step)

### ✅ فاز اول (۲۹ تا ۳۱ شهریور) — Entity & Enum
- طراحی کامل کلاس‌های انتزاعی و اصلی بر اساس UML نهایی.
- ایجاد Enumهای مورد نیاز.

تقسیم وظایف:
- علیرضا:
    - AbstractHotel
    - Branch
    - آماده‌سازی ساختار پروژه و گیتهاب
- شایان:
    - Room
    - Reserve
    - Payment
    - Enums: RoomType, PaymentStatus
- امیرسبحان:
    - Employee
    - Manager
    - Task
    - Enums: Role, TaskStatus
- مهدیار:
    - Guest
    - Property, AssignProperty
    - ارتباط Guest با Reserve و Payment

---

### ✅ فاز دوم (۱ تا ۵ مهر) — Repository Layer
- ایجاد کلاس‌های Repository برای مدیریت CRUD عملیات روی Entityها.
- متدهای اصلی: create, read, update, delete.

تقسیم وظایف:
- علیرضا: Repository برای Branch, Hotel
- شایان: Repository برای Room, Reserve
- امیرسبحان: Repository برای Employee, Task, Manager
- مهدیار: Repository برای Guest, Payment, Property

---

### ✅ فاز سوم (۶ تا ۱۰ مهر) — Service Layer
- پیاده‌سازی لایه Service برای منطق برنامه.
- هر Service وابسته به Repository خودش.

تقسیم وظایف:
- علیرضا: HotelService, BranchService
- شایان: RoomService, ReservationService, PaymentService
- امیرسبحان: EmployeeService, TaskService, ManagerService
- مهدیار: GuestService, PropertyService

---

### ✅ فاز چهارم (۱۱ تا ۱۳ مهر) — Mapper & Util
- ایجاد Mapperها (تبدیل Entity ↔ DTO یا نمایش بهتر).
- کلاس‌های Utility مثل DateUtil, Validator.

تقسیم وظایف:
- علیرضا: HotelMapper, BranchMapper
- شایان: RoomMapper, ReservationMapper, PaymentMapper
- امیرسبحان: EmployeeMapper, TaskMapper, ManagerMapper
- مهدیار: GuestMapper, PropertyMapper, Util classes

---

### ✅ فاز پنجم (۱۴ تا ۱۵ مهر) — Integration & Finalization
- تست نهایی کل سیستم.
- اجرای سناریوهای تست (رزرو اتاق، پرداخت، تخصیص کارمند، مدیریت شعبه و …).
- ادغام کدها در main branch.
- مستندسازی نهایی و آماده‌سازی برای تحویل.

---

## 🎯 Goal
در پایان پروژه، یک سیستم مدیریت هتل به صورت JavaSE (JDK 1.8) با ساختار لایه‌ای (Entity, Repository, Service, Mapper) آماده خواهد شد که تمام نیازهای تعریف‌شده در UML نهایی را پوشش می‌دهد.