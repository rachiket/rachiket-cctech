public class Polygon {

    
	static int Infinite = 10000;
 
	static class Point  
	{
   	 double x;
   	 double y;
 
    	public Point(double x, double y)
    	{
        	this.x = x;
        	this.y = y;
    	}
	};
 
	static boolean isOnSegment(Point p, Point q, Point r)  
	{
    	if (q.x <= Math.max(p.x, r.x) &&
        	q.x >= Math.min(p.x, r.x) &&
        	q.y <= Math.max(p.y, r.y) &&
        	q.y >= Math.min(p.y, r.y))
    	{
        	return true;
    	}
    	return false;
	}
 
	static double orient(Point p, Point q, Point r)  
	{
   	 double val = (q.y - p.y) * (r.x - q.x)
            	- (q.x - p.x) * (r.y - q.y);
 
    	if (val == 0)  
    	{
        	return 0;
    	}
    	return (val > 0) ? 1 : 2;  
	}
 
	static boolean isIntersect(Point p1, Point q1,  
                           	Point p2, Point q2)  
	{
   	 double o1 = orient(p1, q1, p2);
   	 double o2 = orient(p1, q1, q2);
   	 double o3 = orient(p2, q2, p1);
   	 double o4 = orient(p2, q2, q1);
 
    	if (o1 != o2 && o3 != o4)
    	{
        	return true;
    	}
 
    	if (o1 == 0 && isOnSegment(p1, p2, q1))  
    	{
        	return true;
    	}
 
    	if (o2 == 0 && isOnSegment(p1, q2, q1))  
    	{
        	return true;
    	}

   	 
    	if (o3 == 0 && isOnSegment(p2, p1, q2))
    	{
        	return true;
    	}
 
    	if (o4 == 0 && isOnSegment(p2, q1, q2))
    	{
        	return true;
    	}
 
    	return false;  
	}
 
	static boolean isInside(Point polygon[], int n, Point p)
	{
    	if (n < 3)  
    	{
        	return false;
    	}
 
    	Point extreme = new Point(Infinite, p.y);
 
    	int count = 0, i = 0;
    	do
    	{
        	int next = (i + 1) % n;
 
        	if (isIntersect(polygon[i], polygon[next], p, extreme))  
        	{
            	if (orient(polygon[i], p, polygon[next]) == 0)
            	{
                	return isOnSegment(polygon[i], p,
                                 	polygon[next]);
            	}
 
            	count++;
        	}
        	i = next;
    	} while (i != 0);
 
    	return (count % 2 == 1);
	}
 
	public static void main(String[] args)  
	{
    	System.out.println("Input is hardcoded as  Polygon:  [[1,0], [8,3], [8,8], [1,5]] and  Point is :  [3,5] ");
    	Point polygon1[] = {new Point(1, 0),
                        	new Point(8, 3),  
                        	new Point(8, 8),  
                        	new Point(1, 5)};
    	int n = polygon1.length;
    	Point p = new Point(3, 5);
    	if (isInside(polygon1, n, p))
    	{
        	System.out.println("Point lie inside");
    	}  
    	else
    	{
        	System.out.println("Point lie outside");
    	}
    	System.out.println("Input is hardcoded as  Polygon:  [[-3,2], [-2,-0.8], [0,1.2], [2.2,0], [2,4.5]] and Point is : $[0,0]$ ");
    	Point polygon2[] = {new Point(-3, 2),  
                        	new Point(-2,-0.8),
                        	new Point(0, 1.2),
                        	new Point(2.2, 0),
                        	new Point(2,4.5)};
      	p = new Point(0, 0);
      	n = polygon2.length;
      	if (isInside(polygon2, n, p))
      	{
          	System.out.println("Point lie inside");
      	}  
      	else
      	{
          	System.out.println("Point lie outside");
      	}
	}
}
