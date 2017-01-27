# sikinote, http://slpr.sakura.ne.jp/qp/
# Date   : 2016/02/11(yyyy/mm/dd)
# Author : sikino
#    creative commons, CC-BY 4.0

set terminal wxt solid noraise enhanced
set param
set hidden3d
set size squ
set xr[-10:10]
set yr[-10:10]
set zr[-4:4]
set tr[0:2*pi]
set ticslevel 0
set view equal xyz
set view 65,108,2,1
set xl "x"
set yl "y"
unset key
set ur[0:pi]     # theta
set vr[0:2*pi]   # phi
set samples 11
set isosamples 13

# +---polygon---+
s1(x)=x-floor(x)
cnp(n,t)=cos(2e0*pi/real(n)*(s1(t/(2e0*pi/real(n))))-pi/real(n))
pc(n,t)=cos(t)*cos(pi/real(n))/cnp(n,t)
ps(n,t)=sin(t)*cos(pi/real(n))/cnp(n,t)

p3x(n,u,v)=ps(n,u)*pc(n,v)
p3y(n,u,v)=ps(n,u)*ps(n,v)
p3z(n,u)=pc(n,u)

# -- trocoid
hcyc(n,h,t)=(real(n-1)*cos(t)+h*cos(t*real(n-1)))/real(n)
hcys(n,h,t)=(real(n-1)*sin(t)-h*sin(t*real(n-1)))/real(n)

hc3x(n,h,u,v)=hcys(n,h,u)*hcyc(n,h,v)
hc3y(n,h,u,v)=hcys(n,h,u)*hcys(n,h,v)
hc3z(n,h,u)=hcyc(n,h,u)

# -- parent infomation
ces=3e0   # parent size
cesr=0.5e0 # size ratio 
apt=0.2e0   # parent small velocity
lcp=6 # color of parent

# -- around polygon
cs=0.5e0  # size of child
tcn = 8   # number of child
cr0=4.e0   # distance 0 of child from origin
cr1=8.e0   # distance 1 of child from origin
rv=0.05e0 # rotating velocity of child
n=4       # polygon parameter
a=0.2e0  # expand velocity (equal to "apt" recommanded)

at=1e0  # expand velocity by fth

aw=2   # tail change velocity
ww=3e0 # duration of tail
lw=4e0   # length of tail

lcc=6 # color of child

# -- groundchild infomation
gs=0.5e0 # size of groundchild
hgz=4e0  # height of tail
lcg=6 # color of groundchild

fth(a,x,x0,f0,x1,f1)=(f0-f1)*tanh(a*(real(x)-(x0+x1)*0.5e0))/(tanh(a*(real(x0)-(x0+x1)*0.5e0))-tanh(a*(real(x1)-(x0+x1)*0.5e0)))+(-f0*tanh(a*(real(x1)-(x0+x1)*0.5e0))+f1*tanh(a*(real(x0)-(x0+x1)*0.5e0)))/(tanh(a*(real(x0)-(x0+x1)*0.5e0))-tanh(a*(real(x1)-(x0+x1)*0.5e0)))
 # x0,f0 --> x1,f1

fwi(n,r,i,ic)=(real(r)**(2**n))/((real(i-ic))**(2**n)+real(r)**(2**n))
 # window function

#set term gif animate delay 4 size 600,400
#set output 'ramiel.gif'

rv0=rv
rvi=rv
i0=0
i1=51
i2=101
i3=151
i4=201
i5=301
i6=401
i7=461
dth=2e0*pi/real(tcn)
thi=0e0
do for [i=i0:i1-1]{
   thi=thi+rv
   tcx = '' ; tcy = '' ;  tcz = '' ; # center of child, x,y,z of No.j
   do for[j=1:tcn]{
       tcx = tcx . sprintf('%f ', cr0*cos(dth*real(j)+thi))
       tcy = tcy . sprintf('%f ', cr0*sin(dth*real(j)+thi))
       tcz = tcz . sprintf('%f ', 0e0)
   }
   rp3x(j,n,u,v)=cos(thi+dth*j)*p3x(n,u,v)-sin(thi+dth*j)*p3y(n,u,v)
   rp3y(j,n,u,v)=sin(thi+dth*j)*p3x(n,u,v)+cos(thi+dth*j)*p3y(n,u,v)
   rp3z(j,n,u)=p3z(n,u)
   
   splot for[j=1:tcn] \
     cs*fth(a,i,i0,0e0,i1-1,1e0)*rp3x(j,n,u,v)+fth(a,i,i0,0e0,i1-1,1e0)*word(tcx,j),\
     cs*fth(a,i,i0,0e0,i1-1,1e0)*rp3y(j,n,u,v)+fth(a,i,i0,0e0,i1-1,1e0)*word(tcy,j),\
     cs*fth(a,i,i0,0e0,i1-1,1e0)*rp3z(j,n,u)+fth(a,i,i0,0e0,i1-1,1e0)*word(tcz,j) lc lcc, \
     ces*fth(apt,i,i0,1e0,(i1-1),cesr)*rp3x(0,n,u,v),\
     ces*fth(apt,i,i0,1e0,(i1-1),cesr)*rp3y(0,n,u,v),\
     ces*rp3z(0,n,u) lc lcp
}

do for [i= i1 : i2-1 ]{
   thi=thi+rv
   tcx = ''; tcy = ''; tcz = ''; # center of child, x,y,z of No.j
   do for[j=1:tcn]{
       tcx = tcx . sprintf('%f ', fth(at,i,i1,cr0,i2-1,cr1)*cos(dth*real(j)+thi))
       tcy = tcy . sprintf('%f ', fth(at,i,i1,cr0,i2-1,cr1)*sin(dth*real(j)+thi))
       tcz = tcz . sprintf('%f ', 0e0)
   }

   rp3x(j,n,u,v)=cos(thi+dth*j)*p3x(n,u,v)*( j!=0 & (v<0.5e0*pi || v>1.5*pi) ? 1e0+lw*fwi(aw,ww,i,(i1+i2-1)/2):1e0) -sin(thi+dth*j)*p3y(n,u,v)
   rp3y(j,n,u,v)=sin(thi+dth*j)*p3x(n,u,v)*( j!=0 & (v<0.5e0*pi || v>1.5*pi) ? 1e0+lw*fwi(aw,ww,i,(i1+i2-1)/2):1e0) +cos(thi+dth*j)*p3y(n,u,v)
   rp3z(j,n,u)=p3z(n,u)
   
   splot for[j=1:tcn] \
     cs*rp3x(j,n,u,v)+word(tcx,j),\
     cs*rp3y(j,n,u,v)+word(tcy,j),\
     cs*rp3z(j,n,u)+word(tcz,j) lc lcc, \
     ces*fth(apt,i,i1,cesr,i2-1,cesr)*rp3x(0,n,u,v), \
     ces*fth(apt,i,i1,cesr,i2-1,cesr)*rp3y(0,n,u,v), \
     ces*fth(apt,i,i1,1e0,i2-1,cesr)*rp3z(0,n,u) lc lcp
}

cr2=0e0
rv0=rv

do for [i= i2 : i3-1 ]{
   thi=thi+rv
   rv=fth(0.1e0,i,i2,rv0,i3-1,rv0*2e0)

   tcx = ''; tcy = ''; tcz = ''; # center of child, x,y,z of No.j
   do for[j=1:tcn]{
       tcx = tcx . sprintf('%f ', fth(at,i,i2,cr1,i3-1,cr2)*cos(dth*real(j)+thi))
       tcy = tcy . sprintf('%f ', fth(at,i,i2,cr1,i3-1,cr2)*sin(dth*real(j)+thi))
       tcz = tcz . sprintf('%f ', 0e0)
   }

   rp3x(j,n,u,v)=cos(thi+dth*j)*p3x(n,u,v)*( j != 0 ? ((v>0.5e0*pi & v<1.5*pi) ? 1e0+lw*fwi(aw,ww,i,(i2+i3-1)/2) : 1e0+lw*fth(aw,i,i2,0e0,i3-1,2.5e0)):1e0) -sin(thi+dth*j)*p3y(n,u,v)
   rp3y(j,n,u,v)=sin(thi+dth*j)*p3x(n,u,v)*( j != 0 ? ((v>0.5e0*pi & v<1.5*pi) ? 1e0+lw*fwi(aw,ww,i,(i2+i3-1)/2) : 1e0+lw*fth(aw,i,i2,0e0,i3-1,2.5e0)):1e0) +cos(thi+dth*j)*p3y(n,u,v)
   rp3z(j,n,u)=p3z(n,u)
   
   splot for[j=1:tcn] \
     cs*rp3x(j,n,u,v)+word(tcx,j),\
     cs*rp3y(j,n,u,v)+word(tcy,j),\
     cs*rp3z(j,n,u)+word(tcz,j) lc lcc, \
     ces*cesr*rp3x(0,n,u,v), \
     ces*cesr*rp3y(0,n,u,v), \
     ces*cesr*rp3z(0,n,u) lc lcp

}
rv0=rv

cr3=cr2
larm=4e0
do for [i= i3 : i4-1 ]{
   thi=thi+rv
   rv=fth(0.1e0,i,i3,rv0,i4-1,rv0*1.5e0)
   tcx = ''; tcy = ''; tcz = ''; # center of child, x,y,z of No.j

   do for[j=1:tcn]{
       tcx = tcx . sprintf('%f ', fth(at,i,i3,cr2,i4-1,cr3)*cos(dth*real(j)+thi))
       tcy = tcy . sprintf('%f ', fth(at,i,i3,cr2,i4-1,cr3)*sin(dth*real(j)+thi))
       tcz = tcz . sprintf('%f ', 0e0)
   }

   rp3x(j,n,u,v)=cos(thi+dth*j)*p3x(n,u,v)*( j != 0 ? ((v>0.5e0*pi & v<1.5*pi) ? 1e0:1e0+lw*fth(apt,i,i3,2.5e0,i4-1,larm)):1e0) -sin(thi+dth*j)*p3y(n,u,v)
   rp3y(j,n,u,v)=sin(thi+dth*j)*p3x(n,u,v)*( j != 0 ? ((v>0.5e0*pi & v<1.5*pi) ? 1e0:1e0+lw*fth(apt,i,i3,2.5e0,i4-1,larm)):1e0) +cos(thi+dth*j)*p3y(n,u,v)
   rp3z(j,n,u)=p3z(n,u)
   
   splot for[j=1:tcn] \
     cs*rp3x(j,n,u,v)+word(tcx,j),\
     cs*rp3y(j,n,u,v)+word(tcy,j),\
     cs*rp3z(j,n,u)+word(tcz,j) lc lcc, \
     ces*cesr*rp3x(0,n,u,v), \
     ces*cesr*rp3y(0,n,u,v), \
     ces*cesr*rp3z(0,n,u) lc lcp
}
rv0=rv

cr4=cr3
do for [i= i4 : i5-1 ]{
   thi=thi+rv
   rv=fth(0.1e0,i,i4,rv0,i5-1,rv0*2e0)
   tcx = ''; tcy = ''; tcz = '';  # center of child,       x,y,z of No.j
   tgx = ''; tgy = ''; tgz = '';  # center of groundchild, x,y,z of No.j
   do for[j=1:tcn]{
       tcx = tcx . sprintf('%f ', fth(at,i,i4,cr3,i5-1,cr4)*cos(dth*real(j)+thi))
       tcy = tcy . sprintf('%f ', fth(at,i,i4,cr3,i5-1,cr4)*sin(dth*real(j)+thi))
       tcz = tcz . sprintf('%f ', 0e0)
       tgx = tgx . sprintf('%f ', 0.5e0*(1e0+larm*lw+fth(at,i,i4,cr3,i5-1,cr4))*cos(dth*real(j)+thi))
       tgy = tgy . sprintf('%f ', 0.5e0*(1e0+larm*lw+fth(at,i,i4,cr3,i5-1,cr4))*sin(dth*real(j)+thi))
       tgz = tgz . sprintf('%f ', 0e0)
   }

   rp3x(j,n,u,v)=cos(thi+dth*j)*p3x(n,u,v)*( j != 0 ? ((v>0.5e0*pi & v<1.5*pi) ? 1e0:1e0+larm*lw):1e0) -sin(thi+dth*j)*p3y(n,u,v)
   rp3y(j,n,u,v)=sin(thi+dth*j)*p3x(n,u,v)*( j != 0 ? ((v>0.5e0*pi & v<1.5*pi) ? 1e0:1e0+larm*lw):1e0) +cos(thi+dth*j)*p3y(n,u,v)
   rp3z(j,n,u)=p3z(n,u)

   rpgx(j,n,u,v)=cos(thi+dth*j)*p3x(n,u,v)-sin(thi+dth*j)*p3y(n,u,v)
   rpgy(j,n,u,v)=sin(thi+dth*j)*p3x(n,u,v)+cos(thi+dth*j)*p3y(n,u,v)
   rpgz(j,n,u)=p3z(n,u)*( j != 0 ? ((u>0.5e0*pi) ? 1e0+fth(apt,i,i4,0e0,i5-1,hgz):1e0 ):1e0)
   
   splot for[j=1:tcn] \
     cs*rp3x(j,n,u,v)+word(tcx,j),\
     cs*rp3y(j,n,u,v)+word(tcy,j),\
     cs*rp3z(j,n,u)+word(tcz,j) lc lcc,\
     for[k=1:tcn] \
     gs*fth(a,i,i4,0e0,(i4+i5-1)/2,1e0)*rpgx(k,n,u,v)+word(tgx,k),\
     gs*fth(a,i,i4,0e0,(i4+i5-1)/2,1e0)*rpgy(k,n,u,v)+word(tgy,k),\
     gs*fth(a,i,i4,0e0,(i4+i5-1)/2,1e0)*rpgz(k,n,u)+word(tgz,k) lc lcg,\
     ces*cesr*rp3x(0,n,u,v), \
     ces*cesr*rp3y(0,n,u,v), \
     ces*cesr*rp3z(0,n,u) lc lcp
}
rv0=rv


cr5=cr4
do for [i= i5 : i6-1 ]{
   thi=thi+rv
   rv=(i>(i5+i6-1)/2+(i5-i6+1)*3e0/8e0 ? 0e0 : fth(0.2e0,i,i5,rv0,(i5+i6-1)/2+(i5-i6+1)*3e0/8e0,0e0))
   tcx = ''; tcy = ''; tcz = '';  # center of child,       x,y,z of No.j
   tgx = ''; tgy = ''; tgz = '';  # center of groundchild, x,y,z of No.j
   do for[j=1:tcn]{
       tcx = tcx . sprintf('%f ', fth(at,i,i5,cr4,i6-1,cr5)*cos(dth*real(j)+thi))
       tcy = tcy . sprintf('%f ', fth(at,i,i5,cr4,i6-1,cr5)*sin(dth*real(j)+thi))
       tcz = tcz . sprintf('%f ', 0e0)
       tgx = tgx . sprintf('%f ', 0.5e0*(1e0+larm*lw+fth(at,i,i5,cr4,i6-1,cr5))*cos(dth*real(j)+thi))
       tgy = tgy . sprintf('%f ', 0.5e0*(1e0+larm*lw+fth(at,i,i5,cr4,i6-1,cr5))*sin(dth*real(j)+thi))
       tgz = tgz . sprintf('%f ', 0e0)
   }

   rp3x(j,n,u,v)=cos(thi+dth*j)*p3x(n,u,v)*( j != 0 ? ((v>0.5e0*pi & v<1.5*pi) ? 1e0:1e0+larm*lw):1e0) -sin(thi+dth*j)*p3y(n,u,v)
   rp3y(j,n,u,v)=sin(thi+dth*j)*p3x(n,u,v)*( j != 0 ? ((v>0.5e0*pi & v<1.5*pi) ? 1e0:1e0+larm*lw):1e0) +cos(thi+dth*j)*p3y(n,u,v)
   rp3z(j,n,u)=p3z(n,u)

   rpgx(j,n,u,v)=cos(thi+dth*real(j))*p3x(n,u,v)-sin(thi+dth*real(j))*p3y(n,u,v)
   rpgy(j,n,u,v)=sin(thi+dth*real(j))*p3x(n,u,v)+cos(thi+dth*real(j))*p3y(n,u,v)
   rpgz(j,n,u)=p3z(n,u)*( j != 0 ? ((u>0.5e0*pi) ? 1e0+fth(apt,i,i5,hgz,(i5+i6-1)/2,0e0):1e0 ):1e0)
   
   if(i <= (i5+i6-1)/2){
   splot for[j=1:tcn] \
     cs*fth(a,i,i5,1e0,(i6-1),0e0)*rp3x(j,n,u,v)+word(tcx,j),\
     cs*fth(a,i,i5,1e0,(i6-1),0e0)*rp3y(j,n,u,v)+word(tcy,j),\
     cs*fth(a,i,i5,1e0,(i6-1),0e0)*rp3z(j,n,u)+word(tcz,j) lc lcc,\
     for[k=1:tcn] \
     gs*fth(0.05e0,i,i5,1e0,(i5+i6-1)/2,0e0)*rpgx(k,n,u,v)+word(tgx,k),\
     gs*fth(0.05e0,i,i5,1e0,(i5+i6-1)/2,0e0)*rpgy(k,n,u,v)+word(tgy,k),\
     gs*fth(0.05e0,i,i5,1e0,(i5+i6-1)/2,0e0)*rpgz(k,n,u)+word(tgz,k) lc lcg,\
     ces*cesr*rp3x(0,n,u,v), \
     ces*cesr*rp3y(0,n,u,v), \
     ces*cesr*rp3z(0,n,u) lc lcp
     } else {
   splot for[j=1:tcn] \
     cs*fth(a,i,i5,1e0,(i6-1),0e0)*rp3x(j,n,u,v)+word(tcx,j),\
     cs*fth(a,i,i5,1e0,(i6-1),0e0)*rp3y(j,n,u,v)+word(tcy,j),\
     cs*fth(a,i,i5,1e0,(i6-1),0e0)*rp3z(j,n,u)+word(tcz,j) lc lcc,\
     ces*cesr*rp3x(0,n,u,v), \
     ces*cesr*rp3y(0,n,u,v), \
     ces*cesr*rp3z(0,n,u) lc lcp
     }
}

nmax=11
do for [i= 0 : 2*nmax ]{
   n=4+(0.5e0*i)
   rp3x(j,n,u,v)=cos(thi+dth*j)*p3x(n,u,v)-sin(thi+dth*j)*p3y(n,u,v)
   rp3y(j,n,u,v)=sin(thi+dth*j)*p3x(n,u,v)+cos(thi+dth*j)*p3y(n,u,v)
   rp3z(j,n,u)=p3z(n,u)
   
   splot ces*fth(0.4e0,n,4,cesr,nmax,1e0)*rp3x(0,n,u,v), \
     ces*fth(0.4e0,n,4,cesr,nmax,1e0)*rp3y(0,n,u,v), \
     ces*fth(0.4e0,n,4,cesr,nmax,1e0)*rp3z(0,n,u) lc lcp
   pause 0.04
}

do for [i= 2*nmax : 1 : -1 ]{
   n=5+(0.5e0*i)

   thi=fth(0.2e0,i,2*nmax,0e0,0,2e0*pi/3e0)
   
   rp3x(n,u,v)=p3x(n,u,v)
   rp3y(n,u,v)=cos(thi)*p3y(n,u,v)-sin(thi)*p3z(n,u)
   rp3z(n,u,v)=sin(thi)*p3y(n,u,v)+cos(thi)*p3z(n,u)

   splot ces*rp3x(n,u,v), \
     ces*rp3y(n,u,v), \
     ces*rp3z(n,u,v) lc lcp
   pause 0.04
}
set isosamples 5*3+1
do for [i= 0 : 10]{
   splot ces*rp3x(n,u,v), \
     ces*rp3y(n,u,v), \
     ces*rp3z(n,u,v) lc lcp, \
     0e0,0e0,0e0
}

set isosamples 13

do for [i= 0 : 2*nmax ]{
   n=5+(0.5e0*i)

   thi=fth(0.2e0,i,0,2e0*pi/3e0,2*nmax,2e0*pi)
   
   rp3x(n,u,v)=p3x(n,u,v)
   rp3y(n,u,v)=cos(thi)*p3y(n,u,v)-sin(thi)*p3z(n,u)
   rp3z(n,u,v)=sin(thi)*p3y(n,u,v)+cos(thi)*p3z(n,u)

   splot ces*rp3x(n,u,v), \
     ces*rp3y(n,u,v), \
     ces*rp3z(n,u,v) lc lcp
   pause 0.04
}

do for [i= 4*nmax : 0 :-1]{
   n=3+(0.5e0*i)

   thi=fth(0.1e0,i,4*nmax,0e0,0,0e0)

   h=1e0
   rh3x(n,h,u,v)=hc3x(n,h,u,v)
   rh3y(n,h,u,v)=cos(thi)*hc3y(n,h,u,v)-sin(thi)*hc3z(n,h,u)
   rh3z(n,h,u,v)=sin(thi)*hc3y(n,h,u,v)+cos(thi)*hc3z(n,h,u)

   splot ces*rh3x(n,h,u,v), \
     ces*rh3y(n,h,u,v), \
     ces*rh3z(n,h,u,v) lc lcp
   pause 0.04
}

n=4
splot ces*hc3x(n,h,u,v), \
      ces*hc3y(n,h,u,v), \
      ces*hc3z(n,h,u) lc lcp

do for [i= 0 : 10]{
   n=4
   h=1e0
   splot ces*(hc3x(n,h,u,v)*fth(0.1e0,i,0,1e0,10,0e0)+p3x(n,u,v)*fth(0.1e0,i,0,0e0,10,1e0)), \
     ces*(hc3y(n,h,u,v)*fth(0.1e0,i,0,1e0,10,0e0)+p3y(n,u,v)*fth(0.1e0,i,0,0e0,10,1e0)), \
     ces*(hc3z(n,h,u)*fth(0.1e0,i,0,1e0,10,0e0)+p3z(n,u)*fth(0.1e0,i,0,0e0,10,1e0)) lc 6
   pause 0.04
}

do for [i= 0 : 10]{
   splot ces*p3x(n,u,v), \
     ces*p3y(n,u,v), \
     ces*p3z(n,u) lc lcp
   replot 0e0,0e0,0e0
}

#set out
#set terminal wxt enhanced