package Management;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Vector;



public class ServiceUtil {
	static Vector<MemberBean> v = null;

	static ServiceUtil serviceUtil = null;

	public static ServiceUtil getInstance() {
		if (serviceUtil == null) {
			serviceUtil = new ServiceUtil();
		}
		return serviceUtil;
	}

	public Vector<MemberBean> getFileValue(String path) {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(new File(path)));
			Object o = ois.readObject();
			if (path.indexOf(".txt") > 0)
				v = (Vector<MemberBean>) o;
		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
			v = null;

		} finally {
			try {
				if (ois != null)
					ois.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return v;
	}

}