from django.shortcuts import render
from django.http import HttpResponse, JsonResponse
from .models import *
from django.core.files.storage import FileSystemStorage
from itsdangerous import Serializer


# Create your views here.

def index(request):
    return render(request,"public/index.html")

def admin_home(request):
    return render(request,"admin/admin_home.html")

def login(request):
    if 'login' in request.POST:
        username = request.POST['uname']
        password = request.POST['pass']
        if Login.objects.filter(username=username,password=password).exists():
            qa = Login.objects.get(username=username,password=password)
            request.session['lid']=qa.pk
            if qa.usertype =='admin':
                return HttpResponse(f"<script>alert('welcome Admin');window.location='/admin_home'</script>")
            elif qa.usertype =='student':
                return HttpResponse(f"<script>alert('welcome Student');window.location=''</script>")
            elif qa.usertype =='user':
                return HttpResponse(f"<script>alert('welcome user');window.location=''</script>")
            else:
                 return HttpResponse(f"<script>alert('invalid username or password');window.location='login'</script>")

        else:
            return HttpResponse(f"<script>alert('invalid username or password');window.location='login'</script>")
    return render(request,'public/login.html')


def admin_changepass(request):
    if 'submit' in request.POST:
            lid=request.session['lid']
            data=Login.objects.get(id=lid)
            newpass = request.POST['newpass']
            confpass = request.POST['confpass']
            if newpass == confpass:
                data.password=confpass
                data.save()
                return HttpResponse(""" <script> alert('changed successfully');window.location='/admin_home'</script>""")
            else:
                return HttpResponse(""" <script> alert('Password doesnot match');window.location='/admin_home'</script>""")
    return render(request,"admin/change_password.html")


def admin_department(request):
    data1=Department.objects.all()
    return render(request,"admin/department.html",{'data1':data1})

def admin_add_department(request):
    if 'submit' in request.POST:
            dep = request.POST['department']
            q=Department(department_name=dep)
            q.save()
            return HttpResponse(""" <script> alert('added successfully');window.location='/admin_department'</script>""")
    return render(request,"admin/department_add.html")

def admin_delete_dep(request,id):
    q=Department.objects.get(id=id)
    q.delete()
    return HttpResponse(""" <script> alert('removed successfully');window.location='/admin_department'</script>""")

def admin_edit_dep(request,id):
    data=Department.objects.get(id=id)
    if 'submit' in request.POST:
        dep=request.POST['department']
       
        data.department_name=dep
        data.save()
        return HttpResponse(f"<script>alert('Updated');window.location='/admin_department'</script>")
    return render(request,'admin/department_edit.html',{'data':data})


def admin_course(request):
    d=Course.objects.all()
    return render(request,"admin/course.html",{'data':d})


def admin_add_course(request):
    d=Department.objects.all()
    if 'submit' in request.POST:
            dep = request.POST['department']
            course = request.POST['coursename']
            type = request.POST['type']
            q=Course(DEPARTMENT_id=dep,course_name=course,type=type)
            q.save()
            return HttpResponse(""" <script> alert('added successfully');window.location='/admin_course'</script>""")
    return render(request,"admin/course_add.html",{'data':d})


def admin_delete_course(request,id):
    q=Course.objects.get(id=id).delete()
    return HttpResponse(""" <script> alert('removed successfully');window.location='/admin_course'</script>""")

def admin_edit_course(request,id):
    data=Course.objects.get(id=id)
    data1=Department.objects.all()
    if 'submit' in request.POST:
        dep = request.POST['department']
        course = request.POST['course']
        type = request.POST['type']
       
        data.DEPARTMENT_id=dep
        data.course_name=course
        data.type=type
        data.save()
        return HttpResponse(""" <script> alert('added successfully');window.location='/admin_course'</script>""")
    return render(request,'admin/course_edit.html',{'data':data,'data1':data1})


def admin_student(request):
    d=Student.objects.all()
    return render(request,"admin/students.html",{'data':d})

def admin_accept_student(request,id):
    data=Login.objects.get(id=id)
    data.usertype='student'
    data.save()
    return HttpResponse(""" <script> alert('added successfully');window.location='/admin_student'</script>""")

def admin_reject_student(request,id):
    data=Login.objects.get(id=id)
    data.usertype='rejected'
    data.save()
    return HttpResponse(""" <script> alert('added successfully');window.location='/admin_student'</script>""")





# def admin_add_student(request):
#     d=Course.objects.all()
#     if 'submit' in request.POST:
#             name = request.POST['name']
#             gender = request.POST['Gender']
#             dob = request.POST['dob']
#             house = request.POST['house']
#             post = request.POST['post']
#             city = request.POST['city']
#             district = request.POST['district']
#             state = request.POST['state']
#             pin = request.POST['pin']
#             course = request.POST['course']
#             sem = request.POST['sem']
#             phone = request.POST['phone']
#             parentsphone = request.POST['parentsphone']
#             uname = request.POST['uname']
#             password = request.POST['pass']
#             photo = request.FILES['photo']

#             import datetime
#             date=datetime.datetime.now().strftime("%Y%m%d-%H%M%S")+".jpg"
#             fs = FileSystemStorage() 
#             fp = fs.save(date, photo)


#             q1=Login(username=uname,password=password,usertype='student')
#             q1.save()
#             q2=Student(student_name=name,gender=gender,dob=dob,house_name=house,post_office=post,
#                        city=city,district=district,state=state,pin_no=pin,sem=sem,
#                        phone_number=phone,parents_phone=parentsphone,COURSE_id=course,
#                        photo=fs.url(fp),LOGIN_id=q1.pk)
#             q2.save()
#             return HttpResponse(""" <script> alert('added successfully');window.location='/admin_student'</script>""")
#     return render(request,"admin/student_add.html",{'data':d})

# def admin_delete_student(request,id):
#     q=Student.objects.get(id=id)
#     q.delete()
#     return HttpResponse(""" <script> alert('removed successfully');window.location='/admin_student'</script>""")

# def admin_edit_student(request,id):
#     data=Student.objects.get(id=id)
#     data1=Course.objects.all()
#     if 'submit' in request.POST:
#         name = request.POST['name']
#         gender = request.POST['Gender']
#         dob = request.POST['dob']
#         house = request.POST['house']
#         post = request.POST['post']
#         city = request.POST['city']
#         district = request.POST['district']
#         state = request.POST['state']
#         pin = request.POST['pin']
#         course = request.POST['course']
#         sem = request.POST['sem']
#         phone = request.POST['phone']
#         parentsphone = request.POST['parentsphone']
#         photo = request.FILES['photo']
       
#         import datetime
#         date=datetime.datetime.now().strftime("%Y%m%d-%H%M%S")+".jpg"
#         fs = FileSystemStorage() 
#         fp = fs.save(date, photo)


#         data.student_name=name
#         data.gender=gender
#         data.dob=dob
#         data.house_name=house
#         data.post_office=post
#         data.city=city
#         data.district=district
#         data. state=state
#         data. pin_no=pin
#         data.sem=sem
#         data.phone_number=phone
#         data.parents_phone=parentsphone
#         data.COURSE_id=course
#         data.photo=fs.url(fp)
#         data.save()
#         return HttpResponse(""" <script> alert('added successfully');window.location='/admin_student'</script>""")
#     return render(request,'admin/student_edit.html',{'data':data,'data1':data1})



def admin_warden(request):
    d=Warden.objects.all()
    return render(request,"admin/warden.html",{'data':d})


def admin_add_warden(request):
    d=Course.objects.all()
    if 'submit' in request.POST:
            fname = request.POST['fname']
            lname = request.POST['lname']
            gender = request.POST['Gender']
            email = request.POST['email']
            phone = request.POST['phone']
            post = request.POST['post']
            city = request.POST['city']
            district = request.POST['district']
            pin = request.POST['pin']
            uname = request.POST['uname']
            password = request.POST['pass']
            photo = request.FILES['photo']

            import datetime
            date=datetime.datetime.now().strftime("%Y%m%d-%H%M%S")+".jpg"
            fs = FileSystemStorage() 
            fp = fs.save(date, photo)


            q1=Login(username=uname,password=password,usertype='warden')
            q1.save()
            q2=Warden(fname=fname,lname=lname,gender=gender,email=email, phone_no=phone,post_office=post,
                       city=city,district=district,pin_no=pin,
                       photo=fs.url(fp),LOGIN_id=q1.pk)
            q2.save()
            return HttpResponse(""" <script> alert('added successfully');window.location='/admin_warden'</script>""")
    return render(request,"admin/warden_add.html",{'data':d})

def admin_delete_warden(request,id):
    q=Warden.objects.get(id=id)
    q.delete()
    return HttpResponse(""" <script> alert('removed successfully');window.location='/admin_warden'</script>""")

def admin_edit_warden(request,id):
    data=Warden.objects.get(id=id)
    if 'submit' in request.POST:
        fname = request.POST['fname']
        lname = request.POST['lname']
        gender = request.POST['Gender']
        email = request.POST['email']
        phone = request.POST['phone']
        post = request.POST['post']
        city = request.POST['city']
        district = request.POST['district']
        pin = request.POST['pin']
        photo = request.FILES['photo']
       
        import datetime
        date=datetime.datetime.now().strftime("%Y%m%d-%H%M%S")+".jpg"
        fs = FileSystemStorage() 
        fp = fs.save(date, photo)


        data.fname=fname
        data.lname=lname
        data.gender=gender
        data.email=email
        data.phone_no=phone
        data.post_office=post
        data.city=city
        data.district=district
        data.pin_no=pin

        data.photo=fs.url(fp)
        data.save()
        return HttpResponse(""" <script> alert('added successfully');window.location='/admin_warden'</script>""")
    return render(request,'admin/warden_edit.html',{'data':data})



# def admin_attendance(request):
#     d=Attendance.objects.all()
#     return render(request,"admin/attendance.html",{'data':d})


def admin_send_notification(request):
    d=Notification.objects.all()
    return render(request,"admin/send_notification.html",{'data':d})

def admin_add_notification(request):
    if 'submit' in request.POST:
            date = request.POST['date']
            desc = request.POST['desc']
           
            q=Notification(date_of_notification=date,description=desc)
            q.save()
            return HttpResponse(""" <script> alert('added successfully');window.location='/admin_send_notification'</script>""")
    return render(request,"admin/add_notification.html")

def admin_delete_notification(request,id):
    q=Notification.objects.get(id=id)
    q.delete()
    return HttpResponse(""" <script> alert('removed successfully');window.location='/admin_send_notification'</script>""")

def admin_view_complaint(request):
    d=Complaint.objects.all()
    return render(request,"admin/complaint_view.html",{'data':d})


def admin_reply_complaint(request,id):
    data=Complaint.objects.get(id=id)
    if 'submit' in request.POST:
        reply = request.POST['reply']
              
        data.reply=reply
        data.save()
        return HttpResponse(""" <script> alert('added successfully');window.location='/admin_view_complaint'</script>""")
    return render(request,'admin/complaint_reply.html',{'data':data})

def admin_view_payments(request):
    d=Payment.objects.all()
    return render(request,"admin/payment_view.html",{'data':d})

def admin_view_roomallocations(request):
    d=RoomAllocation.objects.all()
    return render(request,"admin/roomallocation_view.html",{'data':d})

def admin_view_activities(request):
    students = Student.objects.all()  # Retrieve all students for dropdown
    data1 = data2 = data3 = data4 = None  # Initialize data variables

    if 'submit' in request.POST:
        std_id = request.POST.get('student')  # Get selected student ID from form
        if std_id:
            # Filter data based on selected student
            data1 = Attendance.objects.filter(STUDENT_id=std_id)
            data2 = Maintenance.objects.filter(STUDENT_id=std_id)
            data3 = CheckInCheckOut.objects.filter(STUDENT_id=std_id)
            data4 = AddOnTimeRequest.objects.filter(CHECKINCHECKOUT__STUDENT_id=std_id)  # Fetch add-on time requests

    context = {
        'data': students,
        'data1': data1,
        'data2': data2,
        'data3': data3,
        'data4': data4,
    }
    return render(request, "admin/activities.html", context)

    # return render(request,"admin/activities.html",{'data':d})


def admin_manage_rooms(request):
    d=Room.objects.all()
    if 'submit' in request.POST:
        room_no = request.POST['room_no']
        description = request.POST['description']
        q = Room(room_no=room_no,capacity=description)
        q.save()
        return HttpResponse(""" <script> alert('Room Added');window.location='/manage_room'</script>""")
    return render(request,"admin/manage_room.html",{'d':d})



def admin_view_attendence(request):
    q=Attendance.objects.all()

    return render(request,"admin/view_attendence.html",{'q':q})






 # -----------------------------------andriod-----------------------

def login_and(request):
    username = request.POST['username']
    password = request.POST['password']
    
    print(f"Received login attempt for username: {username}")
    
    if Login.objects.filter(username=username, password=password).exists():
        qa = Login.objects.get(username=username, password=password)
        lid = qa.pk
        print(f"Login successful for user ID: {lid} with usertype: {qa.usertype}")
        
        if qa.usertype == 'student':
            try:
                qd = Student.objects.get(LOGIN_id=lid)
                print(f"Student found: {qd}")
                sid = qd.pk
                return JsonResponse({'status': 'ok', 'lid': lid, 'sid': sid, 'usertype': 'student'})
            except Student.DoesNotExist:
                print("Student does not exist.")
                return JsonResponse({'status': 'no'})
                
               
        
        elif qa.usertype == 'warden':
            try:
                qd = Warden.objects.get(LOGIN_id=lid)
                print(f"warden found: {qd}")
                wid = qd.pk
                return JsonResponse({'status': 'ok', 'lid': lid, 'wid': wid, 'usertype': 'warden'})
            except Warden.DoesNotExist:
                print("Warden does not exist.")
                return JsonResponse({'status': 'no'})
        
        else:
            print("Invalid usertype.")
            return JsonResponse({'status': 'no'})
    else:
        print("Login failed.")
        return JsonResponse({'status':'no'})


def student_registration(request):
    student_name = request.POST['student_name']
    gender = request.POST['gender']
    dob = request.POST['dob']
    house_name = request.POST['house_name']
    post_office = request.POST['post_office']
    pin_no = request.POST['pin_no']
    city = request.POST['city']
    district = request.POST['district']
    state = request.POST['state']
    COURSE = request.POST['course']
    sem = request.POST['sem']
    phone_number = request.POST['phone_number']
    parents_phone = request.POST['parents_phone']

    photo = request.FILES['photo']
    fs=FileSystemStorage()
    vv=fs.save(photo.name,photo)

    username = request.POST['username']
    password = request.POST['password']
    m = Login(username=username,password=password,usertype = 'student')
    m.save()
    print(m,"Login_id")
    m1 = Student(student_name=student_name,gender=gender,house_name=house_name,dob=dob,post_office=post_office,city=city,district=district,state=state,pin_no=pin_no,photo=photo,COURSE_id=COURSE,sem=sem,phone_number=phone_number,parents_phone=parents_phone,LOGIN_id=m.pk)
    m1.save()

    import qrcode
    import uuid
    student_name = m1.student_name.replace(" ", "_")  # Replace spaces with underscores
    path = f"static/qrcode/{student_name}_{uuid.uuid4()}.png"

    img = qrcode.make(str(m1.pk))
    img.save(path)
    return JsonResponse({'status':'no'})

def view_course(request):
    a = Course.objects.all()
    b = []
    for i in a:
        b.append({'id': i.id, 'course_name': i.course_name})
    return JsonResponse({'status': 'ok', "q": b})

def view_rooms(request):
    a = Room.objects.all()
    b = []
    for i in a:
        b.append({'id': i.id, 'room': i.room_no})
    return JsonResponse({'status': 'ok', "q": b})

def wardern_view_students(request):
    a = Student.objects.all()
    print("--------",a)
    bb = []
    for i in a:
        bb.append({'id': i.id, 'student_name': i.student_name})
    return JsonResponse({'status': 'ok', "q": bb})



import datetime


from itsdangerous import Serializer
from django.core.serializers import serialize
from django.http import JsonResponse


# from your_app.models import RoomAllocation  # Import your RoomAllocation model
from datetime import datetime
from datetime import date

from django.http import JsonResponse
from django.core.serializers import serialize


def warden_allocate_room(request):
    
    rid = request.POST.get('rid')
    sid = request.POST.get('sid')
    print("------------",sid)
    
    # Check if the room is already allocated to the student
    if RoomAllocation.objects.filter(ROOM_id=rid, STUDENT_id=sid,status='Allocated').exists():
        return JsonResponse({'status': 'error', 'message': 'Room already allocated to this student'})
    
    else:
        # Proceed to allocate room
        q1 = RoomAllocation(allocate_date=date.today(), status='Allocated', ROOM_id=rid, STUDENT_id=sid)
        q1.save()
        
        serialized_data = serialize('json', [q1])  # Use the serialize method from django.core.serializers
        return JsonResponse({'status': 'ok', 'data': serialized_data})



def student_view_allocated_room(request):
    sid = request.POST['sid']
    print("------------",sid)
    if RoomAllocation.objects.filter(STUDENT_id=sid).exists():
        i = RoomAllocation.objects.get(STUDENT_id=sid)
        print("------------",i.id)
        b = []

        return JsonResponse({'status': 'ok', 'ra_id': i.id, 'Room': i.ROOM.room_no, 'des': i.ROOM.capacity})
    else:
        return JsonResponse({'status': 'no'})


def sendmaintenance_request(request):
    sid = request.POST['sid']
    rid = request.POST['roomid']
    description=request.POST['description']
    print("----------+++++++++++++++++++++++++++++++++---------",rid)
    date = datetime.now().date()
    q = Maintenance(reported_date = date,student_status="requested",STUDENT_id=sid,ROOMALLOCATION_id=rid,description=description)
    q.save()
    serialized_data = serialize('json', [q])  # Use the serialize method from django.core.serializers
    return JsonResponse({'status': 'ok', 'data': serialized_data})

    STUDENT = models.ForeignKey(Student, on_delete=models.CASCADE)
    ROOMALLOCATION = models.ForeignKey(RoomAllocation, on_delete=models.CASCADE)
    reported_date = models.CharField(max_length=100)
    student_status = models.CharField(max_length=100)


def view_sendmaintenance_request(request):
    sid = request.POST['sid']
    rid = request.POST['roomid']
    
    q = Maintenance.objects.filter(STUDENT_id=sid,ROOMALLOCATION_id=rid)
    b = []
    for i in q:
        b.append({'id': i.id, 'description': i.description,"reported_date":i.reported_date,"student_status":i.student_status})
    return JsonResponse({'status': 'ok', "q": b})

def view_students(request):
    a = RoomAllocation.objects.filter()
    b = []
    for i in a:
        b.append({
            'id': i.id,  # Use i.STUDENT.id to get the student's id
            'stdname': i.STUDENT.student_name,
            'course': i.STUDENT.COURSE.course_name,
            'sid': i.STUDENT.id,
            'sem': i.STUDENT.sem,
            'room': i.ROOM.room_no
        })
    return JsonResponse({'status': 'ok', 'q': b})

from django.http import JsonResponse
from .models import RoomAllocation  # Ensure you import the RoomAllocation model

def search_students(request):
    # Get the course name from the POST data and strip spaces
    course_name = request.POST.get('course', '').strip()

    # Debugging: Print the course name being searched
    print(f"Course Name: {course_name}")

    # Start with all RoomAllocation entries
    students = RoomAllocation.objects.all()

    # Filter by course name if provided
    if course_name:
        students = students.filter(STUDENT__COURSE__course_name__icontains=course_name)

    # Debugging: print the actual SQL query
    print(students.query)

    # Prepare the response data
    response_data = []
    if students.exists():  # Check if any students are found
        for i in students:
            response_data.append({
                'id': i.id,  # RoomAllocation ID
                'sid': i.STUDENT.id,  # Student ID
                'stdname': i.STUDENT.student_name,  # Student name
                'course': i.STUDENT.COURSE.course_name,  # Course name
                'sem': i.STUDENT.sem,  # Semester
                'room': i.ROOM.room_no  # Room number
            })

    # Return the data as a JSON response
    return JsonResponse({'status': 'ok', 'q': response_data})



from datetime import date  # Correct import for the date class

def std_present(request):
    sid = request.POST['sid']
    today_date = date.today()  # Get the current date using date.today()

    # Check if a record for this student and date already exists
    existing_record = Attendance.objects.filter(date=today_date, STUDENT_id=sid).first()

    if existing_record:
        # Return a response indicating the attendance is already recorded
        return JsonResponse({'status': 'exists', 'message': 'Attendance already recorded for today'})

    # If no existing record, insert a new attendance
    q = Attendance(date=today_date, status="Present", STUDENT_id=sid)
    q.save()
    serialized_data = serialize('json', [q])
    return JsonResponse({'status': 'ok', 'data': serialized_data})

def std_abscent(request):
    sid = request.POST['sid']
    today_date = date.today()  # Get the current date using date.today()

    # Check if a record for this student and date already exists
    existing_record = Attendance.objects.filter(date=today_date, STUDENT_id=sid).first()

    if existing_record:
        # Return a response indicating the attendance is already recorded
        return JsonResponse({'status': 'exists', 'message': 'Attendance already recorded for today'})

    # If no existing record, insert a new attendance
    q = Attendance(date=today_date, status="Absent", STUDENT_id=sid)
    q.save()
    serialized_data = serialize('json', [q])
    return JsonResponse({'status': 'ok', 'data': serialized_data})


def view_notification(request):
    cv = Notification.objects.all()
    a = []
    for i in cv:
        a.append({
            'id': i.id,  # Corrected to fetch the ID from the instance# Ensure ALLOCATION and BUS are correctly referenced
            'status': i.description,
            'date': i.date_of_notification
        })
    return JsonResponse({'status': 'ok', 'q': a})


def student_view_attendance(request):
    id = request.POST['sid']
    cv =Attendance.objects.filter(STUDENT_id=id)
    a=[]
    for i in cv:
        a.append({'id':i.id,'student_name':i.STUDENT.student_name,'status':i.status,'date':i.date})
    return JsonResponse({'status':'ok','q':a})

def studentpayment(request):
    id = request.POST['sid']
    amount = request.POST['amount']
    date = datetime.date.today()
    pc = Payment(payment_amount=amount,date_of_payment=date,STUDENT_id=id)
    pc.save()
    serialized_data =  serialize('json',[pc,])
    return JsonResponse({'status':'ok'})
  


def checkin(request):
    id=request.POST['sid']
    dates = date.today()
    print(dates)
    times = datetime.now()
    formatted_time = times.strftime("%Y-%m-%d %H:%M:%S")
    q=CheckInCheckOut.objects.filter(STUDENT_id=id,date_time=dates)
    if q:
        q1=q[0].id
        print(q1)
        ss=CheckInCheckOut.objects.get(id=q1)
        ss.c_out_datetime=times
        ss.status="out"
        ss.save()
    else:
        se=CheckInCheckOut(c_in_datetime=formatted_time,STUDENT_id=id,date_time=dates,status="in")
        se.save()
    return JsonResponse({'status':'ok'})



def view_reply(request):
    id = request.POST['sid']
    cv =Complaint.objects.filter(STUDENT_id=id)
    a=[]
    for i in cv:
        a.append({'id':id,'complaints':i.description,'reply':i.reply})
    return JsonResponse({'status':'ok','q':a})



def psend_complaints(request):
    id = request.POST['sid']
    message = request.POST['message']
    pc = Complaint(description=message,reply='pending',STUDENT_id=id)
    pc.save()
    serialized_data =  serialize('json',[pc,])
    return JsonResponse({'status':'ok'})



def view_Checkin(request):
    id = request.POST['sid']
    cv =CheckInCheckOut.objects.filter(STUDENT_id=id)
    a=[]
    for i in cv:
        a.append({'id':i.id,'c_out_datetime':i.c_out_datetime,'c_in_datetime':i.c_in_datetime,'date':i.date_time})
    return JsonResponse({'status':'ok','q':a})


def send_Addontime(request):
    id = request.POST['ssid']
    message = request.POST['reason']
    time = request.POST['time']
    pc = AddOnTimeRequest(estimated_time=time,reason=message,status='pending',CHECKINCHECKOUT_id=id)
    pc.save()
    serialized_data =  serialize('json',[pc,])
    return JsonResponse({'status':'ok'})

from django.http import JsonResponse

def viewalladdontimes(request):
    # Use select_related to reduce the number of queries
    cv = AddOnTimeRequest.objects.select_related('CHECKINCHECKOUT__STUDENT').all()

    # List comprehension to build your response
    response_data = [
        {
            'id': i.id,
            'time': i.estimated_time,
            'reason': i.reason,
            'statuss': i.status,
            'stdname': i.CHECKINCHECKOUT.STUDENT.student_name,
        }
        for i in cv
    ]

    return JsonResponse({'status': 'ok', 'q': response_data})


# def viewalladdontime(request):
#     addon_requests = AddOnTimeRequest.objects.all()
#     print(addon_requests,'llllllllllllll')
#     response_data = []
#     print("----------------",response_data)

#     for i in addon_requests:
#         response_data.append({
#             'id': i.id,
#             'time': i.estimated_time,
#             'reason': i.reason,
#             'status': i.status,
#             # 'stdname': i.CHECKINCHECKOUT.STUDENT.student_name     
            
#         })
#     return JsonResponse({'status': 'ok', 'q': response_data})






def approve_status(request):
    id = request.POST['rid']
    addons = AddOnTimeRequest.objects.filter(id=id)
    
    if addons.exists():
        for addon in addons:
            addon.status = 'approved'  # Update the status
            addon.save()  # Save the changes

        serialized_data = serialize('json', addons)  # Serialize the updated records
        return JsonResponse({'status': 'ok', 'data': serialized_data})
    else:
        return JsonResponse({'status': 'error', 'message': 'No matching records found.'})

def reject_status(request):
    id = request.POST['rid']
    addons = AddOnTimeRequest.objects.filter(id=id)
    
    if addons.exists():
        for addon in addons:
            addon.status = 'rejected'  # Update the status
            addon.save()  # Save the changes

        serialized_data = serialize('json', addons)  # Serialize the updated records
        return JsonResponse({'status': 'ok', 'data': serialized_data})
    else:
        return JsonResponse({'status': 'error', 'message': 'No matching records found.'})

def studentviewaddontime(request):
    id = request.POST['sid']
    q = AddOnTimeRequest.objects.filter(CHECKINCHECKOUT__STUDENT_id=id)

    a=[]
    for i in q:
        a.append({
            'id': i.id,
            'time': i.estimated_time,
            'status': i.status,
        })
    return JsonResponse({'status': 'ok', 'q': a})


def wardenviewmaintancerequest(request):
    # id = request.POST['sid']
    q = Maintenance.objects.all()

    a=[]
    for i in q:
        a.append({
            'id': i.id,
            'maintaince': i.description,
            'status': i.student_status,
            'date': i.reported_date,
            'room': i.ROOMALLOCATION.ROOM.room_no,

        })
    return JsonResponse({'status': 'ok', 'q': a})


def acceptmaintaincerequest(request):

    id = request.POST['rid']
    addons = Maintenance.objects.filter(id=id)
    
    if addons.exists():
        for addon in addons:
            addon.student_status = 'approved'  # Update the status
            addon.save()  # Save the changes

        serialized_data = serialize('json', addons)  # Serialize the updated records
        return JsonResponse({'status': 'ok', 'data': serialized_data})
    else:
        return JsonResponse({'status': 'error', 'message': 'No matching records found.'})



