from django.db import models

# Create your models here.

class Login(models.Model):
    username = models.CharField(max_length=200)
    password = models.CharField(max_length=200)
    usertype = models.CharField(max_length=200)

class Department(models.Model):
    department_name = models.CharField(max_length=200) 

class Course(models.Model):
    DEPARTMENT = models.ForeignKey(Department, on_delete=models.CASCADE)
    course_name = models.CharField(max_length=200)
    type = models.CharField(max_length=200)   


class Student(models.Model):
    LOGIN = models.ForeignKey(Login, on_delete=models.CASCADE)
    student_name = models.CharField(max_length=200)
    gender = models.CharField(max_length=200)
    dob = models.CharField(max_length=200)
    house_name = models.CharField(max_length=100)
    post_office = models.CharField(max_length=100)
    city = models.CharField(max_length=50)
    district = models.CharField(max_length=50)
    state = models.CharField(max_length=50)
    pin_no = models.CharField(max_length=200)
    COURSE = models.ForeignKey(Course, on_delete=models.CASCADE)
    sem = models.CharField(max_length=200)
    phone_number = models.CharField(max_length=200)
    parents_phone = models.CharField(max_length=200)
    photo = models.CharField(max_length=200)  


class Warden(models.Model):
    LOGIN = models.ForeignKey(Login, on_delete=models.CASCADE)
    fname = models.CharField(max_length=100)
    lname = models.CharField(max_length=100)
    gender = models.CharField(max_length=100)
    email = models.CharField(max_length=100)
    phone_no = models.CharField(max_length=100)
    post_office = models.CharField(max_length=100)
    city = models.CharField(max_length=100)
    district = models.CharField(max_length=100)
    pin_no = models.CharField(max_length=100)
    photo = models.CharField(max_length=200)    


class Attendance(models.Model):
    STUDENT = models.ForeignKey(Student, on_delete=models.CASCADE)
    date =  models.CharField(max_length=100)
    status = models.CharField(max_length=100)



class Room(models.Model):
    room_no = models.CharField(max_length=100)
    capacity =models.CharField(max_length=100)

class RoomAllocation(models.Model):
    STUDENT = models.ForeignKey(Student, on_delete=models.CASCADE)
    ROOM = models.ForeignKey(Room, on_delete=models.CASCADE)
    allocate_date = models.CharField(max_length=200)
    status = models.CharField(max_length=200)    

class Notification(models.Model):
    date_of_notification = models.CharField(max_length=100)
    description = models.CharField(max_length=100)    


class Complaint(models.Model):
    STUDENT = models.ForeignKey(Student, on_delete=models.CASCADE)
    description = models.CharField(max_length=100)
    date = models.CharField(max_length=100)
    reply = models.CharField(max_length=100,null=True)    

class Payment(models.Model):
    STUDENT = models.ForeignKey(Student, on_delete=models.CASCADE)
    payment_amount = models.CharField(max_length=100)
    date_of_payment = models.CharField(max_length=100)
    # month_of_rent = models.CharField(max_length=100)  

class Maintenance(models.Model):
    STUDENT = models.ForeignKey(Student, on_delete=models.CASCADE)
    ROOMALLOCATION = models.ForeignKey(RoomAllocation, on_delete=models.CASCADE)
    reported_date = models.CharField(max_length=100)
    student_status = models.CharField(max_length=100)
    description=models.CharField(max_length=500,default="100")


class CheckInCheckOut(models.Model):
    STUDENT = models.ForeignKey(Student, on_delete=models.CASCADE)
    c_out_datetime = models.CharField(max_length=100)
    c_in_datetime = models.CharField(max_length=100,null=True)
    date_time = models.CharField(max_length=200)
    status = models.CharField(max_length=100)    

class AddOnTimeRequest(models.Model):
    CHECKINCHECKOUT = models.ForeignKey(CheckInCheckOut,on_delete=models.CASCADE)
    estimated_time = models.CharField(max_length=100)
    reason = models.CharField(max_length=100)
    status = models.CharField(max_length=100)        
